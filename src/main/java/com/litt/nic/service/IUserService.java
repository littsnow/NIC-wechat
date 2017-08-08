package com.litt.nic.service;

import com.litt.nic.pojo.user;

public interface IUserService {
	user findById(int id);

	void addUser(com.litt.nic.pojo.user user);

	user findByOpenid(String openid);
}
