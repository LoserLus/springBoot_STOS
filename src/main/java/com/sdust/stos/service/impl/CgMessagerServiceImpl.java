package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.CgMessager;
import com.sdust.stos.mapper.CgMessagerMapper;
import com.sdust.stos.service.CgMessagerService;
import org.springframework.stereotype.Service;

@Service
public class CgMessagerServiceImpl extends ServiceImpl<CgMessagerMapper, CgMessager> implements CgMessagerService {
}
