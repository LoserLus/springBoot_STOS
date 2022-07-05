package com.sdust.stos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdust.stos.common.R;
import com.sdust.stos.dto.DgListDto;
import com.sdust.stos.entity.FxMessager;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface FxMessagerService extends IService<FxMessager> {

    public R<List<DgListDto>> dglist();

    public R<String> release(@RequestBody DgListDto dgListDto);
}
