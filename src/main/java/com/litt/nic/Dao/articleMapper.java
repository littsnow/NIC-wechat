package com.litt.nic.Dao;

import com.litt.nic.pojo.article;

public interface articleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(article record);

    int insertSelective(article record);

    article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(article record);

    int updateByPrimaryKey(article record);
    
    int findMaxId();
}