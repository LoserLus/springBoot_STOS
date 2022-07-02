package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.DgList;
import com.sdust.stos.mapper.DgListMapper;
import com.sdust.stos.service.DgListService;
import org.springframework.stereotype.Service;

@Service
public class DgListServiceImpl extends ServiceImpl<DgListMapper, DgList> implements DgListService {
}
