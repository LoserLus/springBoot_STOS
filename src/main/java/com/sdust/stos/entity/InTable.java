package com.sdust.stos.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class InTable implements Serializable {

    //id
    private Integer id;

    //书号
    @TableId(value = "isbn")
    private String isbn;

    //书籍库存
    private Integer stock;
}
