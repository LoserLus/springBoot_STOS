package com.sdust.stos.dto;

import com.sdust.stos.entity.DgList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DgListDto extends DgList{

    //private List<DgList> list = new ArrayList<>();

    //书名
    private String bookName;

    //书籍库存
    private Integer stock;


}
