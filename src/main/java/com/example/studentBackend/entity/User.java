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
import java.util.Date;


/**
 * 系统用户表
 * @author Savor
 * @version 2025-01-31 13:32:17
 */
@Data
@Table(name = "base_user")
public class User implements Serializable {

    @Id
    @KeySql(genId = SnowflakeGenId.class)
    @FieldToString
    private Long id;
	
	 /**
     *登陆账号
     */
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
	
	 /**
     *用户姓名
     */
    @Column(name = "name")
    private String name;
	
	 /**
     *手机号
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "email")
    private String email;

    @Column(name = "sex")
    private String sex;
	
	 /**
     * 年级
     */
    @Column(name = "year")
    private String year;

    /**
     * 专业
     */
    @Column(name = "major")
    private String major;

    @Column(name = "crt_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date crtTime;

    @Column(name = "crt_user_id")
    private String crtUserId;

    @Column(name = "crt_user_name")
    private String crtUserName;

    @Column(name = "upd_time")
    private Date updTime;

    @Column(name = "upd_user_id")
    private String updUserId;

    @Column(name = "upd_user_name")
    private String updUserName;

    @Column(name = "attr1")
    private String attr1;

    @Column(name = "attr2")
    private String attr2;

    @Column(name = "attr3")
    private String attr3;

    @Column(name = "attr4")
    private String attr4;
	
	 /**
     *乐观锁标识   每次更新该字段+1
     */
    @Column(name = "version")
    private Integer version;
	
	 /**
     *是否禁用
     */
    @Column(name = "is_disabled")
    private String isDisabled;
	
	 /**
     *超级管理员 0-否 1-是
     */
    @Column(name = "is_super_admin")
    private String isSuperAdmin;
	
}
