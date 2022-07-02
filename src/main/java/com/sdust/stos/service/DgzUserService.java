package com.sdust.stos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdust.stos.common.R;
import com.sdust.stos.entity.DgzUser;

public interface DgzUserService extends IService<DgzUser> {

    /**
     * 用户登录
     * @param username
     * @param pwd
     * @param type
     * @return
     */
    public R<String> login(String username,String pwd,String type);
}
