package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.Dgzuser;
import com.sdust.stos.mapper.NmuserMapper;
import com.sdust.stos.service.NmuserService;
import org.springframework.stereotype.Service;

@Service
public class NmuserServiceImpl extends ServiceImpl<NmuserMapper, Dgzuser> implements NmuserService {
}
