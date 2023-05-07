package com.tencent.wxcloudrun.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
    * 商品表
    */
@ApiModel(value="商品表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "product")
public class Product {
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="商品id")
    private Integer id;

    /**
     * 对应shop表的id
     */
    @TableField(value = "shop_id")
    @ApiModelProperty(value="对应shop表的id")
    private Integer shopId;

    /**
     * 分类id,对应product_category表的主键
     */
    @TableField(value = "category_id")
    @ApiModelProperty(value="分类id,对应product_category表的主键")
    private Integer categoryId;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="商品名称")
    private String name;

    /**
     * 商品主图
     */
    @TableField(value = "logo_img")
    @ApiModelProperty(value="商品主图")
    private String logoImg;

    /**
     * 扩展图片地址（逗号分割）
     */
    @TableField(value = "sub_img")
    @ApiModelProperty(value="扩展图片地址（逗号分割）")
    private String subImg;

    @TableField(exist = false)
    private List<String> subImgList;

    @TableField(exist = false)
    private List<String> detailList;

    /**
     * 商品描述
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="商品描述")
    private String remark;

    /**
     * 原价,单位-元保留两位小数
     */
    @TableField(value = "origin_price")
    @ApiModelProperty(value="原价,单位-元保留两位小数")
    private BigDecimal originPrice;

    /**
     * 售价（优惠价）
     */
    @TableField(value = "sell_price")
    @ApiModelProperty(value="售价（优惠价）")
    private BigDecimal sellPrice;

    /**
     * 折扣
     */
    @TableField(value = "discount")
    @ApiModelProperty(value="折扣")
    private BigDecimal discount;

    /**
     * 点赞
     */
    @TableField(value = "likes")
    @ApiModelProperty(value="点赞")
    private Integer likes;

    /**
     * 限购数量
     */
    @TableField(value = "limit_num")
    @ApiModelProperty(value="限购数量")
    private Integer limitNum;

    /**
     * 总销量
     */
    @TableField(value = "total_sales")
    @ApiModelProperty(value="总销量")
    private Integer totalSales;

    /**
     * 月销量
     */
    @TableField(value = "monthly_sales")
    @ApiModelProperty(value="月销量")
    private Integer monthlySales;

    /**
     * 库存数量
     */
    @TableField(value = "stock")
    @ApiModelProperty(value="库存数量")
    private Integer stock;

    /**
     * 商品状态.1-在售 2-下架 3:缺货
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="商品状态.1-在售 2-下架 3:缺货")
    private String status;

    /**
     * 0：未删除 1：已删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="0：未删除 1：已删除")
    private Boolean delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    /**
     * 标签
     */
    @TableField(value = "tags")
    @ApiModelProperty(value="标签")
    private String tags;


    @TableField(exist = false)
    private MultipartFile file;

    @TableField(exist = false)
    private String categoryName;

    @TableField(value = "detail_remark")
    private String detailRemark;

    @TableField(value = "detail_img")
    private String detailImg;

    @TableField(exist = false)
    private Integer salesCount;

    public static final String COL_ID = "id";

    public static final String COL_SHOP_ID = "shop_id";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_NAME = "name";

    public static final String COL_LOGO_IMG = "logo_img";

    public static final String COL_SUB_IMG = "sub_img";

    public static final String COL_REMARK = "remark";

    public static final String COL_ORIGIN_PRICE = "origin_price";

    public static final String COL_SELL_PRICE = "sell_price";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_LIKES = "likes";

    public static final String COL_LIMIT_NUM = "limit_num";

    public static final String COL_TOTAL_SALES = "total_sales";

    public static final String COL_MONTHLY_SALES = "monthly_sales";

    public static final String COL_STOCK = "stock";

    public static final String COL_STATUS = "status";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_TAGS = "tags";
}