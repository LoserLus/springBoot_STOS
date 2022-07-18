package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.LsList;
import com.sdust.stos.service.LsListService;
import com.sdust.stos.mapper.LsListMapper;
import org.springframework.stereotype.Service;

@Service
public class LsListServiceImpl extends ServiceImpl<LsListMapper, LsList> implements LsListService {
}
