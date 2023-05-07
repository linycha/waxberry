package com.tencent.wxcloudrun.modules.dto;

import lombok.Data;

@Data
public class OrderStatusCountDTO {
    private Integer unPayCount;
    private Integer unDeliveryCount;
    private Integer commitCount;
    private Integer allCount;
}
