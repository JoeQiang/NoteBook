package com.notebook.pojo;

import java.util.Date;

public class User {
	private int ID;
	private String name;
	private String nickname;
	private String password;
	private Date birthday;
	private String signature;

	public User() {
		super();
	}

	public User(int iD, String name, String nickname, String password) {
		ID = iD;
		this.name = name;
		this.nickname = nickname;
		this.password = password;
	}

	public User(String name, String nickname, String password, Date birthday,
			String signature) {
		this.name = name;
		this.nickname = nickname;
		this.password = password;
		this.birthday = birthday;
		this.signature = signature;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + ", nickname=" + nickname
				+ ", password=" + password + ", birthday=" + birthday
				+ ", signature=" + signature + "]";
	}

}
