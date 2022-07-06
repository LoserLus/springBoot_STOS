package com.sdust.stos.dto;

import com.sdust.stos.entity.JsList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JsListDto extends JsList {

    //采购数量
    private Integer cgNumber;
}
