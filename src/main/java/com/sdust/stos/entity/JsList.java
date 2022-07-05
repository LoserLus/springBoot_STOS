package com.sdust.stos.entity;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class JsList {

    //进书编号
    private String jsId;

    //书号
    private String isbn;

    //采购人账号
    private String cgUsername;

    //采购日期
    private LocalDateTime cgDate;

    //采购总数
    private Integer cgTotal;

    //采购总额
    private Integer cgAmount;

    //采购标记
    private Integer cgFlag;
}
