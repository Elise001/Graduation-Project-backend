package com.example.studentBackend.dto;

import com.example.studentBackend.annotation.FieldToString;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表
 * @author Savor
 * @version 2025-01-31 13:32:17
 */
@Data
public class UserDto implements Serializable {

    @FieldToString
    private Long id;
	
	 /**
     *登陆账号
     */
    private String username;

    private String password;
    
	 /**
     *用户姓名
     */
    private String name;
    
	 /**
     *手机号
     */
    private String mobilePhone;

    private String email;

    private String sex;

    /**
     * 年级
     */
    private String year;

    /**
     * 专业
     */
    private String major;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date crtTime;

    private String crtUserId;

    private String crtUserName;

    private Date updTime;

    private String updUserId;

    private String updUserName;

    private String attr1;

    private String attr2;

    private String attr3;

    private String attr4;
    
	 /**
     *乐观锁标识   每次更新该字段+1
     */
    private Integer version;
    
	 /**
     *是否禁用
     */
    private String isDisabled;
    
	 /**
     *超级管理员 0-否 1-是
     */
    private String isSuperAdmin;
    
}
