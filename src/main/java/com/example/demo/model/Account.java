package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.User;

@Component
@SessionScope
public class Account {

	/**自分のユーザー**/
	private User user;

	public Account() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserId() {
		return user.getId();
	}

	public String getUserName() {
		return user.getUserName();
	}
}
