package com.demo6.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    String name;
    @JSONField(format = "yyyy/MM/dd")
    LocalDate date;

}
