package com.ms.base.bean.login;

import java.io.Serializable;

public class LoginUser implements Serializable {

	private static final long serialVersionUID = 9065147185051289362L;
	private String bearer;
	private String host;

	private Long id;
	private Long userSn;
	private String name;

	private String tel;
	private String email;

	private Long roleId;
	private String roleName;
	
	private String accessKeyId;
	
	public LoginUser(){}

	public LoginUser(String bearer, String host) {
		super();
		this.bearer = bearer;
		this.host = host;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getUserSn() {
		return userSn;
	}

	public void setUserSn(Long userSn) {
		this.userSn = userSn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
