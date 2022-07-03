package com.sdust.stos.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdust.stos.entity.InTable;
import com.sdust.stos.mapper.InTableMapper;
import com.sdust.stos.service.InTableService;
import org.springframework.stereotype.Service;

@Service
public class InTableServiceImpl extends ServiceImpl<InTableMapper, InTable> implements InTableService {
}
