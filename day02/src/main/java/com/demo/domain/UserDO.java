package com.demo.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class UserDO {
    @NotNull(message = "名字不能为空")
    @Size(min = 2 ,max = 10, message = "长度在2到10之间")
    private String name;

    @JSONField(format = "yyyy-MM-dd")
    private LocalDateTime time;

    @JSONField(serialize = true)
    private String ignore;
}
