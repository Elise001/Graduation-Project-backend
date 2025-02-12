package com.example.studentBackend.dto;

import com.example.studentBackend.annotation.FieldToString;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门-用户关系表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Data
public class DepartUserDto implements Serializable {
	
	 /**
     *【主键ID】雪花ID
     */
    @FieldToString
    private Long id;
	
	 /**
     *【用户ID】
     */
     @FieldToString
    private Long userId;
    
	 /**
     *【部门ID】
     */
     @FieldToString
    private Long departId;
    
	 /**
     *【路径】
     */
    private String path;
    
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
