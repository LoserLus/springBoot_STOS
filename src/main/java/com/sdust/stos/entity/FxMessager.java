package com.sdust.stos.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class FxMessager implements Serializable {

    //发行人账号
    private String fxUsername;

    //发行人密码
    private String fxPassword;

    //发行人姓名
    private String fxName;

    //发行人电话
    private String fxTel;
}
