package com.demo03.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class User {
    String name;
    LocalDateTime time;
}
