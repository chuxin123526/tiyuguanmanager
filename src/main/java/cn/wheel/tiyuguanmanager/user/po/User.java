package cn.wheel.tiyuguanmanager.user.po;

import java.util.HashSet;
import java.util.Set;

public class User {
	private long userId;
	private String username;
	private String password;
	private int gender;
	private Role role;
	private String studentNumber;
	private String realname;
	private int identifierType;
	private String identifierNumber;
	private Set<Contract> contracts;
	private int status;
	private int type;

	public User() {
		this.contracts = new HashSet<>();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		this.userId = id;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
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

	public Set<Contract> getContracts() {
		return contracts;
	}

	public void setContracts(Set<Contract> contracts) {
		this.contracts = contracts;
	}

	public boolean hasPermission(int type) {
		return this.role.hasPermission(type);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
