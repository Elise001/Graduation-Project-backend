package com.example.studentBackend.dto;

import com.example.studentBackend.annotation.FieldToString;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 教材订单表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Data
public class TextbookOrderDto implements Serializable {
	
	 /**
     *【主键ID】雪花ID
     */
     @FieldToString
    private Long id;
	
	 /**
     *【订单编号】
     */
    private String orderCode;
    
	 /**
     *【年级】
     */
    private String year;
    
	 /**
     *【专业】
     */
    private String major;
    
	 /**
     *【教材编号】
     */
    private String textbookCode;
    
	 /**
     *【教材名字】
     */
    private String textbookName;
    
	 /**
     *【教材数量】
     */
    private BigDecimal count;
    
	 /**
     *【教材单价】
     */
    private BigDecimal price;
    
	 /**
     *【订单状态】
     */
    private String orderStatus;
    
	 /**
     *【激活标识】0:失效,1:有效
     */
    private String isActive;
    
	 /**
     *【乐观锁标识】每次更新该字段+1
     */
    private Integer version;
    
	 /**
     *【扩张字段1】
     */
    private String ref1;
    
	 /**
     *【扩张字段2】
     */
    private String ref2;
    
	 /**
     *【扩张字段3】
     */
    private String ref3;
    
	 /**
     *【扩张字段4】
     */
    private String ref4;
    
	 /**
     *【创建人】
     */
    private String crtUserName;
    
	 /**
     *【创建人ID】
     */
    private String crtUserId;
    
	 /**
     *【创建时间】
     */
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date crtTime;
    
	 /**
     *【最后更新人】
     */
    private String updUserName;
    
	 /**
     *【最后更新人ID】
     */
    private String updUserId;
    
	 /**
     *【最后更新时间】
     */
    private Date updTime;
    
}
