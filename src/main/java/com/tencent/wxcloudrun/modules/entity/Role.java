package com.tencent.wxcloudrun.modules.entity;

import lombok.Data;

import java.io.Serializable;

/**
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 2550610506071566713L;
    private String id;
    private String name;
}
