package com.raynor.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("t_dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键(自增)
     */
    @TableId(value = "t_id", type = IdType.AUTO)
    private Long id;

    /**
     * 列名称
     */
    @TableField(value = "t_col_name")
    private String dictionaryColName;

    /**
     * 字典名称
     */
    @TableField(value = "t_name")
    private String dictionaryName;

    /**
     * 字典描述
     */
    @TableField(value = "t_desc")
    private String dictionaryDesc;

    /**
     * 字典表明
     */
    @TableField(value = "t_table_name")
    private String dictionaryTable;

    /**
     * 创建时间
     */
    @TableField(value = "t_create_time")
    private LocalDateTime createTime;

    /**
     * 是否启用(1:启用,0:禁用)
     */
    @TableField(value = "t_state")
    private String state;

    /**
     * 顺序
     */
    @TableField(value = "t_order")
    private String dictionaryOrder;
}
