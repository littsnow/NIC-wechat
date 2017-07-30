package com.litt.nic.service;

import com.litt.nic.pojo.user;

public interface IUserService {
	user findById(int id);

	void addUser(String name, String telephone);
}
