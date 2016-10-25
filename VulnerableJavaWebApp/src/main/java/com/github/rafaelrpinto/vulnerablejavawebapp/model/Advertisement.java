package com.github.rafaelrpinto.vulnerablejavawebapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * An advertisement made by an user.
 * 
 * @author Rafael
 *
 */
public class Advertisement implements Serializable {

	private static final long serialVersionUID = 1743217303847332506L;

	private int id;
	private Date createDate;
	private String title;
	private String text;
	private User user;

	public Advertisement() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
