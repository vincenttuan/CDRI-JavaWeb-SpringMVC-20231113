package guestbook.model;

import java.util.Date;

/**
 * 定義每一筆留言的資訊(POJO) 
 * author:
 * version:
 * date: 
 * */
public class Guestbook {
	private Integer id;       // 序號
	private String  nickname; // 暱稱
	private Integer age;      // 年齡	
	private String  sex;      // 性別
	private Date    date;     // 時間
	
	public Guestbook() {
		
	}
	
	public Guestbook(Integer id, String nickname, Integer age, String sex, Date date) {
		this.id = id;
		this.nickname = nickname;
		this.age = age;
		this.sex = sex;
		this.date = date;
	}





	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
