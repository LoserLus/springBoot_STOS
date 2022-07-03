package com.sdust.stos.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgList;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.FxMessager;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.service.DgListService;
import com.sdust.stos.service.FxMessagerService;
import com.sdust.stos.service.TextMessageService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/messager")
@Api(tags = {"FxMessagerController"},description = "发行人Controller")
public class FxMessagerController {

    @Autowired
    private FxMessagerService fxMessagerService;

    @Autowired
    private DgListService dgListService;

    @Autowired
    private TextMessageService textMessageService;

    /**
     * 获取用户订购书籍列表
     * @return
     */
    @GetMapping("/dglist")
    public R<List<DgListDto>> dglist() {

        log.info("获取用户订购书籍列表..");

        LambdaQueryWrapper<DgList> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.orderByAsc(DgList::getDgDate);

        //获取订购列表
        List<DgList> dgList = dgListService.list(queryWrapper);

        //通过stream流遍历dgList,
        List<DgListDto> dgListDto = dgList.stream().map((item)->{

            //创建一个dgListDto
            DgListDto dgListDto1 = new DgListDto();

            //copy dgList到dgListDto
            BeanUtils.copyProperties(item,dgListDto1);

            //获取这条订购记录的书号
            String isbn = item.getIsbn();

            TextMessage textMessage = textMessageService.getById(isbn);

            //设置书的名称
            if(textMessage != null){
                String bookName = textMessage.getBookName();
                dgListDto1.setBookName(bookName);
            }

            return dgListDto1;
        }).collect(Collectors.toList());

        return R.success(dgListDto);
    }

}
