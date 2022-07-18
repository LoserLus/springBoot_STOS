package com.sdust.stos.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgList;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.LsList;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.mapper.DgzUserMapper;
import com.sdust.stos.service.DgListService;
import com.sdust.stos.service.DgzUserService;
import com.sdust.stos.service.LsListService;
import com.sdust.stos.service.TextMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DgzUserServiceImpl extends ServiceImpl<DgzUserMapper, DgzUser> implements DgzUserService {


    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private DgListService dgListService;

    @Autowired
    private LsListService lsListService;


    @Override
    public R<String> register(DgzUser dgzUser) {
        //先根据账号在数据库中查找
        LambdaQueryWrapper<DgzUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DgzUser::getDgzUsername, dgzUser.getDgzUsername());
        int count = this.count(queryWrapper);

        //如果已经存在该账号，返回注册错误
        if (count > 0) {
            return R.error("已经存在这个账号");
        } else {
            //如果不存在该账号，则把这个注册的用户增加到dgzuser表中
            this.save(dgzUser);
        }

        return R.success("注册成功");
    }

    @Override
    public R<List<TextMessage>> textlist() {
        LambdaQueryWrapper<TextMessage> queryWrapper = new LambdaQueryWrapper<>();
        List<TextMessage> list = textMessageService.list(queryWrapper);

        return R.success(list);
    }

    @Override
    public R<String> textorder(HttpServletRequest request, List<DgListDto> list) {
        //在session中设置当前登录的用户账号
        String nowusername = (String) request.getSession().getAttribute("nowusername");

        for (int i = 0; i < list.size(); i++) {
            //从列表中获取当个书籍
            DgListDto dgListDto = list.get(i);

            //查询当前书籍的单价是多少
            TextMessage textMessage = textMessageService.getById(dgListDto.getIsbn());

            int price = textMessage.getPrice();

            //首先要判断当前用户是否已经订购过这本书

            //根据书号和当前用户账号在数据库订购记录中查询有没有记录
            LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(DgList::getIsbn,dgListDto.getIsbn());
            queryWrapper.eq(DgList::getDgzUsername,nowusername);

            DgList DgOne = dgListService.getOne(queryWrapper);

            //如果当前用户已经订购过这本书，则修改这条记录的dgTotal值就行
            if (DgOne != null) {
                //把之前订购的书籍加上这一次订购的数量
                int dgAllTotal = DgOne.getDgTotal() + dgListDto.getDgTotal();
                DgOne.setDgTotal(dgAllTotal);
                DgOne.setDgAmount(price * dgAllTotal);
                DgOne.setDgDate(LocalDateTime.now());

                //执行更新操作
                LambdaQueryWrapper<DgList> queryWrapper2 = new LambdaQueryWrapper<>();
                queryWrapper2.eq(DgList::getDgId,DgOne.getDgId());
                dgListService.update(DgOne,queryWrapper2);

            }else{

                //如果当前用户没有订购过这本书，把这个书籍信息放入订购表中
                dgListDto.setDgAmount(price * dgListDto.getDgTotal());
                dgListDto.setDgDate(LocalDateTime.now());
                dgListDto.setDgId("DG" + System.currentTimeMillis());
                dgListDto.setDgzUsername(nowusername);
                dgListDto.setStatus(1);

                dgListService.save(dgListDto);
            }

        }

        return R.success("成功订购");
    }


    public R<List<LsList>> getText(HttpServletRequest request){

        //得到当前登录用户的账号，得到这个用户的订购信息
        String nowusername = (String) request.getSession().getAttribute("nowusername");
        LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DgList::getDgzUsername,nowusername);

        //得到当前用户订购的书籍的订单号，可以有多个订单号
        List<DgList> dglist = dgListService.list(queryWrapper);

        ArrayList<LsList> lsLists = new ArrayList<>();

        for(int i=0;i<dglist.size();i++){
            String dgId = dglist.get(i).getDgId();
            //根据这些订单号得到当前用户的领书单
            LambdaQueryWrapper<LsList> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(LsList::getDgId,dgId);
            LsList one = lsListService.getOne(queryWrapper1);
            if(one != null){
                lsLists.add(one);
            }
        }

        return R.success(lsLists);
    }
}
