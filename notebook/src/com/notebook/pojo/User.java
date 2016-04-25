package com.notebook.pojo;

public class User {
	private int ID;
	private String name;
	private String nickname;
	private String password;

	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}

	public User(int iD, String name, String nickname, String password) {
		super();
		ID = iD;
		this.name = name;
		this.nickname = nickname;
		this.password = password;
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

	@Override
	public String toString() {
		return "User [ID=" + ID + ", name=" + name + ", nickname=" + nickname + ", password=" + password + "]";
	}


}
