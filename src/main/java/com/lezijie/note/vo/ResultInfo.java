package com.lezijie.note.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultInfo<T> {

    private Integer code;
    private String msg;
    private T result;

}
