package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.JsListDto;
import com.sdust.stos.entity.CgMessager;
import com.sdust.stos.entity.InTable;
import com.sdust.stos.entity.JsList;
import com.sdust.stos.service.CgMessagerService;
import com.sdust.stos.service.InTableService;
import com.sdust.stos.service.JsListService;
import com.sdust.stos.mapper.CgMessagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CgMessagerServiceImpl extends ServiceImpl<CgMessagerMapper, CgMessager> implements CgMessagerService {


    @Autowired
    private JsListService jsListService;

    @Autowired
    private InTableService inTableService;

    @Override
    public R<List<JsList>> getJsList() {

        List<JsList> list = jsListService.list();

        return R.success(list);
    }

    @Override
    @Transactional
    public R<String> purchase(HttpServletRequest request, List<JsListDto> list) {


        for(int i=0;i<list.size();i++){

            JsListDto jsList = list.get(i);


            //首先执行采购功能，就是让书库中的库存量增加
            //1根据书号在库存表中获取这本书的信息，得到它原来的库存
            LambdaQueryWrapper<InTable> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(InTable::getIsbn,jsList.getIsbn());
            InTable inTable1 = inTableService.getOne(queryWrapper2);
            Integer stock = inTable1.getStock();

            //创建一个库存对象，设置属性，然后更新其数据
            InTable inTable = new InTable();
            inTable.setIsbn(jsList.getIsbn());
            inTable.setStock(stock + jsList.getCgNumber());

            LambdaQueryWrapper<InTable> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(InTable::getIsbn,jsList.getIsbn());
            inTableService.update(inTable,queryWrapper1);


            //1获取采购人的账号加入进书表中
            String nowusername = (String) request.getSession().getAttribute("nowusername");
            jsList.setCgUsername(nowusername);

            //2获取当前时间作为采购时间加入进书表中
            jsList.setCgDate(LocalDateTime.now());

            //3把进书表中的标识字段改为1，代表已经采购了，库存已经增加了
            jsList.setCgFlag(1);

            //4设置采购人采购单的数量
            jsList.setCgTotal(jsList.getCgNumber());


            LambdaQueryWrapper<JsList> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(JsList::getJsId,jsList.getJsId());

            jsListService.update(jsList,queryWrapper);

        }

        return R.success("操作成功");
    }


}
