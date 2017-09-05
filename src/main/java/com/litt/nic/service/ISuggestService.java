package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.suggest;

public interface ISuggestService {

	void addsuggest(com.litt.nic.pojo.suggest suggest);

	List<suggest> searchAll();

	void deletesuggest(int id);

}
