package com.litt.nic.service;

import com.litt.nic.pojo.manager;

public interface IManagerService {
	manager findById(int id);

	manager findByNamePsw(String name, String psw);
}
