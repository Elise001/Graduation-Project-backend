package com.example.studentBackend.entity;

import com.example.studentBackend.annotation.FieldToString;
import com.example.studentBackend.config.SnowflakeGenId;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 * @author Savor
 * @version 2025-02-12 14:04:48
 */
@Data
@Table(name = "base_depart")
public class Depart implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 /**
     *【主键ID】雪花ID
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @FieldToString
    private Long id;
	
	 /**
     *【部门编码】
     */
    @Column(name = "depart_code")
    private String departCode;
	
	 /**
     *【部门名称】
     */
    @Column(name = "depart_name")
    private String departName;
	
	 /**
     *【部门权限】
     */
    @Column(name = "permission")
    private String permission;
	
	 /**
     *【激活标识】0:失效,1:有效
     */
    @Column(name = "is_active")
    private String isActive;
	
	 /**
     *【乐观锁标识】每次更新该字段+1
     */
    @Column(name = "version")
    private Integer version;
	
	 /**
     *【扩张字段1】
     */
    @Column(name = "ref1")
    private String ref1;
	
	 /**
     *【扩张字段2】
     */
    @Column(name = "ref2")
    private String ref2;
	
	 /**
     *【扩张字段3】
     */
    @Column(name = "ref3")
    private String ref3;
	
	 /**
     *【扩张字段4】
     */
    @Column(name = "ref4")
    private String ref4;
	
	 /**
     *【创建人】
     */
    @Column(name = "crt_user_name")
    private String crtUserName;
	
	 /**
     *【创建人ID】
     */
    @Column(name = "crt_user_id")
    private String crtUserId;
	
	 /**
     *【创建时间】
     */
    @Column(name = "crt_time")
    private Date crtTime;
	
	 /**
     *【最后更新人】
     */
    @Column(name = "upd_user_name")
    private String updUserName;
	
	 /**
     *【最后更新人ID】
     */
    @Column(name = "upd_user_id")
    private String updUserId;
	
	 /**
     *【最后更新时间】
     */
    @Column(name = "upd_time")
    private Date updTime;
	
}
