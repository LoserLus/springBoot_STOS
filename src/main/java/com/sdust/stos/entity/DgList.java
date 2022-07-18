package com.sdust.stos.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class DgList implements Serializable {


    //订购单号

    private String dgId;

    //订购者账号
    private String dgzUsername;

    //书号
    @TableId(value = "isbn")
    private String isbn;

    //日期
    private LocalDateTime dgDate;

    //购书总数
    private Integer dgTotal;

    //总额
    private Integer dgAmount;

    //状态 0:已发放，1:未发放
    private Integer status;
}
