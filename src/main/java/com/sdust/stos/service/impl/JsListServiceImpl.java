package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.JsList;
import com.sdust.stos.mapper.JsListMapper;
import com.sdust.stos.service.JsListService;
import org.springframework.stereotype.Service;

@Service
public class JsListServiceImpl extends ServiceImpl<JsListMapper, JsList> implements JsListService {
}
