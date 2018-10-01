package com.rk.entity;


import com.rk.common.Validation.MaxLength;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;

/**
 * product_type 实体类
 * Thu Sep 20 15:49:27 CST 2018 Qin_Yikai
 */

public class ProductType {

    /**
     * id
     */
    private int id;

    /**
     * parentTypeId
     */
    private Integer parentTypeId;

    /**
     * typeName
     */
    @NotBlank(message = "类型名称不能为空。")
    @MaxLength(value = 12, message = "类型名称不能超过12个字符。")
    private String typeName;

    /**
     * typeCode
     */
    @NotBlank(message = "类型代码不能为空。")
    @MaxLength(value = 12, message = "类型代码不能超过12个字符。")
    private String typeCode;

    /**
     * 0:停用， 1: 正常
     */
    private short status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getParentTypeId() {
        return parentTypeId;
    }

    public void setParentTypeId(Integer parentTypeId) {
        this.parentTypeId = parentTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

}

