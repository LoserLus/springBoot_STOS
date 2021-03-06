package com.sdust.stos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.dto.QsListDto;
import com.sdust.stos.entity.FxMessager;
import com.sdust.stos.entity.JsList;
import com.sdust.stos.entity.QsList;
import com.sdust.stos.entity.TextMessage;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FxMessagerService extends IService<FxMessager> {

    public R<List<DgListDto>> dglist();

    public R<String> release(HttpServletRequest request, @RequestBody DgListDto dgListDto);

    public R<String> sendLockB(HttpServletRequest request,List<QsListDto> list);

    public R<List<QsListDto>> getLockB();

    public R<String> purchase(List<QsList> list);

    public R<List<JsList>> textOk();

    public R<String> delectJsList(List<String> ids);

    public R<List<TextMessage>> getBookList();

    public R<String> deleteBook(String isbn);

    public R<String> addBook(TextMessage newBook);

    public R<String> updateBook(TextMessage newBook);
}
