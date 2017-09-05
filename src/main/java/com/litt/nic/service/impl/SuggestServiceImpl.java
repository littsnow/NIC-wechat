package com.litt.nic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.suggestMapper;
import com.litt.nic.pojo.suggest;
import com.litt.nic.service.ISuggestService;

@Service
public class SuggestServiceImpl implements ISuggestService {

	@Autowired
	private suggestMapper suggestMapper;

	@Override
	public void addsuggest(suggest suggest) {
		suggestMapper.insert(suggest);

	}

	@Override
	public List<suggest> searchAll() {

		List<suggest> suggests = suggestMapper.selectAllSuggest();
		return suggests;
	}

	@Override
	public void deletesuggest(int id) {

		suggestMapper.deleteByPrimaryKey(id);
	}

}
