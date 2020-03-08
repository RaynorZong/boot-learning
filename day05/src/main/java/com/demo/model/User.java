package com.demo.model;

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

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String password;

    private LocalDateTime time;

    private String records;


}
