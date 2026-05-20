package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.entity.Setting;
import com.example.demo.entity.User;

@Component
@SessionScope
public class Account {

	/**自分のユーザー**/
	private User user;

	/**自分の設定**/
	private Setting setting;

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

	public Setting getSetting() {
		return setting;
	}

	public void setSetting(Setting setting) {
		this.setting = setting;
	}

	public Boolean getSettingIsShowAll() {
		return setting.isShowAll();
	}

	public Integer getSettingShowGenreId() {
		return setting.getShowGenreId();
	}

	public void setSettingIsShowAll(Boolean bool) {
		this.setting.setShowAll(bool);
	}

	public void setSettingShowGenreId(Integer id) {
		this.setting.setShowGenreId(id);
	}

}
