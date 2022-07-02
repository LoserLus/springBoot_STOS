package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.TextMessage;
import com.sdust.stos.mapper.TextMessageMapper;
import com.sdust.stos.service.TextMessageService;
import org.springframework.stereotype.Service;

@Service
public class TextMessageServiceImpl extends ServiceImpl<TextMessageMapper, TextMessage> implements TextMessageService {
}
