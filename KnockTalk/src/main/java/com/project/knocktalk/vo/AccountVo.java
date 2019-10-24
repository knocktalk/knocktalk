package com.project.knocktalk.vo;

import org.springframework.web.multipart.MultipartFile;

public class AccountVo {
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_nickname;
	private int user_gender;
	private String user_email;
	private int user_birth;
	private String user_phonenumber;
	private String user_nationality;
	private String user_address;
	private String user_imagepath;
	private String user_imagename;
	private int user_isAdmin;
	private String regdate;
	
	private MultipartFile image;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(int user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_birth() {
		return user_birth;
	}
	public void setUser_birth(int user_birth) {
		this.user_birth = user_birth;
	}
	public String getUser_phonenumber() {
		return user_phonenumber;
	}
	public void setUser_phonenumber(String user_phonenumber) {
		this.user_phonenumber = user_phonenumber;
	}
	public String getUser_nationality() {
		return user_nationality;
	}
	public void setUser_nationality(String user_nationality) {
		this.user_nationality = user_nationality;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_imagepath() {
		return user_imagepath;
	}
	public void setUser_imagepath(String user_imagepath) {
		this.user_imagepath = user_imagepath;
	}
	public String getUser_imagename() {
		return user_imagename;
	}
	public void setUser_imagename(String user_imagename) {
		this.user_imagename = user_imagename;
	}
	public int getUser_isAdmin() {
		return user_isAdmin;
	}
	public void setUser_isAdmin(int user_isAdmin) {
		this.user_isAdmin = user_isAdmin;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
