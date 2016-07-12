package com.jn.excel;

import java.util.Date;

/**
 * Created by death on 2016/7/11.
 */
public class Vo {
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 城市ID
     */
    private Long cityId;
    /**
     * 价格
     */
    private Integer price;
    /**
     * exportTime
     */
    private Date exportTime;

    public Vo(Long productId, Long cityId, Integer price, Date exportTime) {
        this.productId = productId;
        this.cityId = cityId;
        this.price = price;
        this.exportTime = exportTime;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getExportTime() {
        return exportTime;
    }

    public void setExportTime(Date exportTime) {
        this.exportTime = exportTime;
    }
}
