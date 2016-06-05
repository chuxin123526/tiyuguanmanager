package cn.wheel.tiyuguanmanager.user.vo;

import java.util.Set;

import cn.wheel.tiyuguanmanager.user.po.Contract;

public class UserVO {
	private long userId;

	private String username;
	private String password;
	private int gender;

	private Set<Contract> contracts;
	private int identifierType;
	private String identifierNumber;

	private String studentNumber;
	private String realname;

	private String mobilePhone;
	private String roleName;

	private int accountType;
	private int roleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	public int getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(int identifierType) {
		this.identifierType = identifierType;
	}

	public String getIdentifierNumber() {
		return identifierNumber;
	}

	public void setIdentifierNumber(String identifierNumber) {
		this.identifierNumber = identifierNumber;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
