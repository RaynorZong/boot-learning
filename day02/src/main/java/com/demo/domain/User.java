package com.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    String name;
    @JSONField(format = "yyyy-MM-dd")
    LocalDateTime time;
}
