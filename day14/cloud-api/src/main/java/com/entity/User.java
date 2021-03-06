package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Raynor
 * @since 2020-02-28
 */

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Data
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String password;

    private LocalDateTime time;

    private String records;


}
