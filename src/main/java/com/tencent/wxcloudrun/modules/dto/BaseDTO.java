package com.tencent.wxcloudrun.modules.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 */
@Data
public class BaseDTO implements Serializable {
    /**
     * 当前页
     */
    private int current;
    /**
     * 当前页大小
     */
    private int size;
}
