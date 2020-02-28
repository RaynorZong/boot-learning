package com.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * (TMessage)实体类
 *
 * @author makejava
 * @since 2020-02-19 12:28:09
 */
@Data
public class Message implements Serializable {
    private static final long serialVersionUID = 147747791174405211L;
    
    private String xlh;
    
    private String userXlh;
    
    private String articleXlh;
    
    private String content;
    
    private Integer isVisible;
    
    private LocalDate createDate;
    
    private LocalDateTime createTime;
    
    private Integer likeNum;



}