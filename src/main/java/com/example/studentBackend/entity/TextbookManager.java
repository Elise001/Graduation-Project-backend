package com.example.studentBackend.entity;

import com.example.studentBackend.annotation.FieldToString;
import com.example.studentBackend.config.SnowflakeGenId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 教材管理表
 * @author Savor
 * @version 2025-02-14 12:51:45
 */
@Data
@Table(name = "textbook_manager")
public class TextbookManager implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 /**
     *【主键ID】雪花ID
     */
    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @FieldToString
    private Long id;
	
	 /**
     *【教材编号】
     */
    @Column(name = "textbook_code")
    private String textbookCode;
	
	 /**
     *【教材名字】
     */
    @Column(name = "textbook_name")
    private String textbookName;
	
	 /**
     *【年级】
     */
    @Column(name = "year")
    private String year;
	
	 /**
     *【专业】
     */
    @Column(name = "major")
    private String major;
	
	 /**
     *【教材单价】
     */
    @Column(name = "price")
    private BigDecimal price;
	
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
