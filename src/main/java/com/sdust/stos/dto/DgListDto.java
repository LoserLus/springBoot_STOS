package com.sdust.stos.dto;

import com.sdust.stos.entity.DgList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DgListDto extends DgList{

    private List<DgList> list = new ArrayList<>();

    private String bookName;


}
