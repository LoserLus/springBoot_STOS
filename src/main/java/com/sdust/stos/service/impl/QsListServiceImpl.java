package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.QsList;
import com.sdust.stos.service.QsListService;
import com.sdust.stos.mapper.QsListMapper;
import org.springframework.stereotype.Service;

@Service
public class QsListServiceImpl extends ServiceImpl<QsListMapper, QsList> implements QsListService {
}
