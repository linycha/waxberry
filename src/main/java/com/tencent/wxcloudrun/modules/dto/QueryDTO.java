package com.tencent.wxcloudrun.modules.dto;

import lombok.Data;

/**
 * 查询dto
 * @Description TODO
 */
@Data
public class QueryDTO extends BaseDTO {

    private String selfNumbering;

    private String pileNum;

    private String title;

    private String name;
    private String code;

    private String content;

    private String queryType;

    private String address;

    private String userId;
    private String status;

    private Integer productId;

    private Integer shippingId;
    private Integer categoryId;

    private String orderNo;

}
