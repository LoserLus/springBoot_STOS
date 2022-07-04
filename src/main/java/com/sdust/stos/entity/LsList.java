package com.sdust.stos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class LsList implements Serializable {

    //领书id
    private String lsId;

    //订购单号
    private String dgId;

    //领书日期
    private LocalDateTime lsDate;

    //发行人账号
    private String lsUsername;

}
