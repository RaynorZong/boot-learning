package com.demo.model;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Article {
    private String xlh;
    private String user_xlh;
    private String cover_url;
    private String title;
    private String digest;
    private String is_original;
    private String is_top;
    private String status;
    private String is_mass;
    private String content;
    @JSONField(format = "yyyy-MM-dd")
    private LocalDate create_date;
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime create_time;
    private String original_url;
    private Integer read_num;

}
