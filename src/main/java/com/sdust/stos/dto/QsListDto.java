package com.sdust.stos.dto;

import com.sdust.stos.entity.QsList;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QsListDto extends QsList {

    //书名
    private String bookName;


}
