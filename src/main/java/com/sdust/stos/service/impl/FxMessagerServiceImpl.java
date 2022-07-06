package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.dto.QsListDto;
import com.sdust.stos.entity.*;
import com.sdust.stos.mapper.FxMessagerMapper;
import com.sdust.stos.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FxMessagerServiceImpl extends ServiceImpl<FxMessagerMapper, FxMessager> implements FxMessagerService {

    @Autowired
    private DgListService dgListService;

    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private InTableService inTableService;

    @Autowired
    private LsListService lsListService;

    @Autowired
    private QsListService qsListService;

    @Autowired
    private JsListService jsListService;


    @Override
    public R<List<DgListDto>> dglist() {
        LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(DgList::getDgDate);

        //获取订购列表
        List<DgList> dgList = dgListService.list(queryWrapper);

        //通过stream流遍历dgList,
        List<DgListDto> dgListDto = dgList.stream().map((item) -> {

            //创建一个dgListDto
            DgListDto dgListDto1 = new DgListDto();

            //copy dgList到dgListDto
            BeanUtils.copyProperties(item, dgListDto1);

            //获取这条订购记录的书号
            String isbn = item.getIsbn();

            TextMessage textMessage = textMessageService.getById(isbn);

            //设置书的名称
            if (textMessage != null) {
                String bookName = textMessage.getBookName();
                dgListDto1.setBookName(bookName);
            }

            //从书库中获取这本书的库存
            LambdaQueryWrapper<InTable> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(InTable::getIsbn, isbn);
            InTable inTable = inTableService.getOne(queryWrapper1);

            if (inTable != null) {
                Integer stock = inTable.getStock();
                dgListDto1.setStock(stock);
            }

            return dgListDto1;
        }).collect(Collectors.toList());

        return R.success(dgListDto);
    }

    @Override
    @Transactional
    public R<String> release(HttpServletRequest request, DgListDto dgListDto) {

        //在session中设置当前登录的用户账号
        String nowusername = (String) request.getSession().getAttribute("nowusername");

        //根据书号从书库获取这本书的库存信息
        LambdaQueryWrapper<InTable> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InTable::getIsbn, dgListDto.getIsbn());
        InTable inTable = inTableService.getOne(queryWrapper);

        //修改这本书的库存数量
        Integer dgTotal = dgListDto.getDgTotal();
        Integer stock = dgListDto.getStock();
        int nowStock = stock - dgTotal;

        //把这本书的库存重新更新到库存表中
        inTable.setStock(nowStock);
        inTableService.update(inTable, queryWrapper);

        //这个订书单完成，加入到领书单里面
        LsList lsList = new LsList();
        lsList.setLsId("LS" + System.currentTimeMillis());
        lsList.setDgId(dgListDto.getDgId());
        lsList.setLsDate(LocalDateTime.now());
        lsList.setLsUsername(nowusername);
        lsListService.save(lsList);

        //把这个订单删除
        LambdaQueryWrapper<DgList> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(DgList::getDgId, dgListDto.getDgId());
        dgListService.remove(queryWrapper1);

        return R.success("发书成功");
    }

    /**
     * 发送缺书单到缺书表中
     *
     * @return
     */
    @Override
    @Transactional
    public R<String> sendLockB(HttpServletRequest request, List<QsListDto> list) {

        //在session中设置当前登录的用户账号
        String nowusername = (String) request.getSession().getAttribute("nowusername");

        for (int i = 0; i < list.size(); i++) {

            //从缺书表中获取单个记录，赋值给一个缺书对象
            QsList qsList = list.get(i);
            qsList.setQsId("Qs" + System.currentTimeMillis());
            qsList.setQsUsername(nowusername);

            //获取缺的书的订购单号
            String dgId = list.get(i).getDgId();

            //把这个缺书记录加到缺书表中
            qsListService.save(qsList);

            //把这个订购单删除
            //LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();
            //queryWrapper.eq(DgList::getDgId,dgId);
            //dgListService.remove(queryWrapper);


        }

        return R.success("发送缺书单成功");
    }

    /**
     * 获取缺书单
     *
     * @return
     */
    @Override
    public R<List<QsListDto>> getLockB() {

        List<QsList> qsList = qsListService.list();

        //通过stream流遍历qsList,
        List<QsListDto> qsListDto = qsList.stream().map((item) -> {

            //创建一个qsListDto
            QsListDto qsListDto1 = new QsListDto();

            //copy qsList到qsListDto
            BeanUtils.copyProperties(item, qsListDto1);

            //获取这条订购记录的书号
            String isbn = item.getIsbn();

            TextMessage textMessage = textMessageService.getById(isbn);

            //设置书的名称
            if (textMessage != null) {
                String bookName = textMessage.getBookName();
                qsListDto1.setBookName(bookName);
            }
            return qsListDto1;

        }).collect(Collectors.toList());

        return R.success(qsListDto);
    }

    /**
     * 发行人采购功能
     *
     * @return
     */
    @Override
    @Transactional
    public R<String> purchase(List<QsList> list) {

        for (int i = 0; i < list.size(); i++) {

            //前端传来需要采购人采购的书籍订单

            //新建一个进书对象
            JsList jsList = new JsList();

            //设置进书的书号
            jsList.setIsbn(list.get(i).getIsbn());

            //设置进书的数量
            jsList.setCgTotal(list.get(i).getQsTotal());

            //设置进书的进书单号和
            jsList.setJsId("JS" + System.currentTimeMillis());

            //得到这个书的价格
            String isbn = jsList.getIsbn();
            TextMessage textMessage = textMessageService.getById(isbn);

            if(textMessage != null){
                Integer price = textMessage.getPrice();
                //设置进书的总额
                jsList.setCgAmount(price * list.get(i).getQsTotal());

            }

            //设置是否已经采购标识
            jsList.setCgFlag(0);

            //保存到进书表中
            jsListService.save(jsList);

            //把这个缺书单给删除
            LambdaQueryWrapper<QsList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(QsList::getQsId,list.get(i).getQsId());

            qsListService.remove(queryWrapper);

        }

        return R.success("操作成功");
    }


    public R<List<JsList>> textOk(){

        List<JsList> list = jsListService.list();

        return R.success(list);
    }


    public R<String> delectJsList(List<String> ids){

        jsListService.removeByIds(ids);

        return R.success("操作成功");
    }
}
