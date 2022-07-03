package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgList;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.service.DgListService;
import com.sdust.stos.service.DgzUserService;
import com.sdust.stos.service.TextMessageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = {"DgzUserController"},description = "订购者Controller")
public class DgzUserController {

    @Autowired
    private DgzUserService dgzUserService;

    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private DgListService dgListService;

    /**
     * 普通用户登录（老师学生）
     * @param request
     * @param username
     * @param pwd
     */
    @GetMapping("/login")
    public R<String> Login(HttpServletRequest request, String username, String pwd) {
        log.info("username: {}", username);
        log.info("pwd: {}", pwd);

        //在session中设置当前登录的用户账号
        request.getSession().setAttribute("nowusername", username);

        //教师和学生登录
        //1.根据用户账号查询该账号是否在数据库中
        LambdaQueryWrapper<DgzUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DgzUser::getDgzUsername, username);
        DgzUser oneDgzUser = dgzUserService.getOne(queryWrapper);

        //3.如不在，返回错误结果
        if (oneDgzUser == null) {
            return R.error("登录失败");
        }

        //4。如在，比较该密码是否匹配，如不匹配，返回错误结果
        if (!oneDgzUser.getDgzPassword().equals(pwd)) {
            return R.error("登录失败");
        }

        return R.success("登录成功");

    }


    /**
     * 学生注册
     *
     * @param dgzUser
     * @return
     */
    @PostMapping("/register")
    public R<String> register(@RequestBody DgzUser dgzUser) {
        log.info("dgzUser : {}", dgzUser);

        //先根据账号在数据库中查找
        LambdaQueryWrapper<DgzUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DgzUser::getDgzUsername, dgzUser.getDgzUsername());
        int count = dgzUserService.count(queryWrapper);

        //如果已经存在该账号，返回注册错误
        if (count > 0) {
            return R.error("已经存在这个账号");
        } else {
            //如果不存在该账号，则把这个注册的用户增加到dgzuser表中
            dgzUserService.save(dgzUser);
        }

        return R.success("注册成功");
    }

    /**
     * 获取仓库里面的书籍列表
     *
     * @return
     */
    @GetMapping("/textlist")
    public R<List<TextMessage>> textlist() {

        log.info("获取仓库里面的书籍列表..");
        LambdaQueryWrapper<TextMessage> queryWrapper = new LambdaQueryWrapper<>();
        List<TextMessage> list = textMessageService.list(queryWrapper);

        return R.success(list);
    }

    /**
     * 用户确认订购,存入订购表
     *
     * @param list
     * @return
     */
    @PostMapping("/textorder")
    public R<String> textorder(HttpServletRequest request, @RequestBody List<DgListDto> list) {
        for (int i = 0; i < list.size(); i++) {
            DgListDto dgListDto = list.get(i);
            log.info("订购的书籍为：{}", dgListDto);
        }

        //在session中设置当前登录的用户账号
        String nowusername = (String) request.getSession().getAttribute("nowusername");

        for (int i = 0; i < list.size(); i++) {
            //从列表中获取当个书籍
            DgListDto dgListDto = list.get(i);

            //查询当前书籍的单价是多少
            TextMessage textMessage = textMessageService.getById(dgListDto.getIsbn());

            int price = textMessage.getPrice();

            //首先要判断当前用户是否已经订购过这本书
            DgList DgOne = dgListService.getById(dgListDto.getDgId());

            //如果当前用户已经订购过这本书，则修改这条记录的dgTotal值就行
            if (DgOne != null && DgOne.getDgzUsername() != null && nowusername.equals(DgOne.getDgzUsername())) {
                //把之前订购的书籍加上这一次订购的数量
                int dgAllTotal = DgOne.getDgTotal() + dgListDto.getDgTotal();
                DgOne.setDgTotal(dgAllTotal);
                DgOne.setDgAmount(price * dgAllTotal);
                DgOne.setDgDate(LocalDateTime.now());
                DgOne.setDgId("DG" + System.currentTimeMillis());

                //执行更新该条数据操作
                LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.setEntity(DgOne);

            }
            //如果当前用户没有订购过这本书，把这个书籍信息放入订购表中
            dgListDto.setDgAmount(price * dgListDto.getDgTotal());
            dgListDto.setDgDate(LocalDateTime.now());
            dgListDto.setDgId("DG" + System.currentTimeMillis());
            dgListDto.setDgzUsername(nowusername);

            dgListService.save(dgListDto);
        }

        return R.success("成功订购");
    }

}
