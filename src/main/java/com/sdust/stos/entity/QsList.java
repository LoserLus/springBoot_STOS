package com.sdust.stos.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class QsList implements Serializable {

    //主键id
    private String qsId;

    //书号
    @TableId(value = "isbn")
    private String isbn;

    //数量
    private Integer qsTotal;

    //发行人（管理员）
    private String qsUsername;


}
