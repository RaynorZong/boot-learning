package com.raynor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Raynor
 * @since 2020-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dictionary_detail")
public class DictionaryDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(自增)
     */
    @TableId(value = "t_id", type = IdType.AUTO)
    private Long id;

    /**
     * dictionary表主键
     */
    @TableField(value = "t_did")
    private Long did;

    /**
     * 通用字典列
     */
    @TableField(value = "col1")
    private String col1;
    /**
     * 通用字典列
     */
    @TableField(value = "col2")
    private String col2;
    /**
     * 通用字典列
     */
    @TableField(value = "col3")
    private String col3;
    /**
     * 通用字典列
     */
    @TableField(value = "col4")
    private String col4;
    /**
     * 通用字典列
     */
    @TableField(value = "col5")
    private String col5;

    /**
     * 创建时间
     */
    @TableField(value = "t_create_time")
    private LocalDateTime createTime;

    /**
     * 审核标志(1:通过,0:不通过)
     */
    @TableField(value = "t_state")
    private String state;


}
