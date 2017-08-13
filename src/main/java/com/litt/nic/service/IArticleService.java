package com.litt.nic.service;

import com.litt.nic.pojo.article;

public interface IArticleService {
	int findMaxId();
	article  findById(int id);
	int save(article article);
}
