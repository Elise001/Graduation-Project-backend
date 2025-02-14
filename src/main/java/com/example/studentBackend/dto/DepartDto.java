package com.example.studentBackend.dto;

import com.example.studentBackend.annotation.FieldToString;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Data
public class DepartDto implements Serializable {
	
	 /**
     *【主键ID】雪花ID
     */
     @FieldToString
    private Long id;
	
	 /**
     *【部门编码】
     */
    private String departCode;
    
	 /**
     *【部门名称】
     */
    private String departName;
    
	 /**
     *【部门权限】
     */
    private String permission;
    
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
