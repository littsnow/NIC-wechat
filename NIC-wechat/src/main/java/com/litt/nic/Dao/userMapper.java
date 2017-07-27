package com.litt.nic.Dao;

import java.util.List;

import com.litt.nic.pojo.user;

public interface userMapper {
	
	/**
	 * 删除
	 * */
	//按ID删除
    int deleteByPrimaryKey(Integer userId);
    //按姓名删除
    int deleteByName(String userName);
    
    
    /**
     * 插入
     * */
    //全部插入
    int insert(user record);

    int insertSelective(user record);
    
    
    /**
     * 查找
     * */
    //根据ID查找
    user selectByPrimaryKey(Integer userId);
    //根据姓名查找
    user selectByName(String userName);
    //多条件模糊查询返回单条信息
    user selectByConditionOne(user record);
    //多条件模糊查询返回多条信息
    List<user> selectByCondition(user record);
    //查出全部用户
    List<user> selectAllUser();
    
    /**
     * 更新
     * */
    //根据ID有选择的更新
    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
    
    
    
}