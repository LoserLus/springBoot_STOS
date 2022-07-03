package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.FxMessager;
import com.sdust.stos.mapper.FxMessagerMapper;
import com.sdust.stos.service.FxMessagerService;
import org.springframework.stereotype.Service;

@Service
public class FxMessagerServiceImpl extends ServiceImpl<FxMessagerMapper, FxMessager> implements FxMessagerService {
}
