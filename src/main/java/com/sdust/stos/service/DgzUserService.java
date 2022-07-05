package com.sdust.stos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.DgzUser;
import com.sdust.stos.entity.TextMessage;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DgzUserService extends IService<DgzUser> {

    public R<String> register(@RequestBody DgzUser dgzUser);

    public R<List<TextMessage>> textlist();

    public R<String> textorder(HttpServletRequest request, @RequestBody List<DgListDto> list);


}
