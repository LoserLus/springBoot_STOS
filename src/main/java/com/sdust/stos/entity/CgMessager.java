package com.sdust.stos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CgMessager {

    //账号
    private String cgUsername;

    //密码
    private String cgPassword;

    //采购人姓名
    private String cgName;

    //采购人电话
    private String cgTel;
}
