package com.sdust.stos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.CgMessager;
import com.sdust.stos.entity.JsList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CgMessagerService extends IService<CgMessager> {

    public R<List<JsList>> getJsList();

    public R<String> purchase(HttpServletRequest request, List<JsList> list);
}
