package com.szht.htfsweb.util;

import java.io.Serializable;

public class UserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;



    private String status;
	private String username;
	private String nickname;
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String password;
	private String writepwd;
	private String isactive;
    private int headimg=0;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getWritepwd() {
		return writepwd;
	}
	public void setWritepwd(String writepwd) {
		this.writepwd = writepwd;
	}
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
	public String getUrlParam(){
		return "&UserName="+ this.username ;
        //+ "&UserPwd=" + this.password;
	}
    public String getName(){
        if(nickname==null||nickname.length()==0){
             return username;
        }
        return nickname;
    }
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

    public int getHeadimg() {
        return headimg;
    }

    public void setHeadimg(int headimg) {
        this.headimg = headimg;
    }
}
