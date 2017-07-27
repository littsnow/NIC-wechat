package com.litt.nic.Service;

import java.util.List;

import com.litt.nic.pojo.user;

public interface userService {
	
	/**
	 * 根据用户id查找用户
	 * */
	user selectByPrimaryKey(Integer userId);
	/**
	 * 根据用户姓名查找用户
	 * */
	user selectByName(String userName);
	/**
	 * 多条件模糊查询返回单条信息
	 * */
    user selectByConditionOne(user record);
	/**
	 * 多条件查找用户返回多条信息
	 * */
	List<user> selectByCondition(user record);
	 /**
	  * 查出全部用户
	  * */
    List<user> selectAllUser();
	
	/**
	 * 插入所有属性，少一个报错
	 * */
	int insert(user record);
//	/**
//	 * 有选择的插入
//	 * */
//	int insertSelective(user record);
	
	/**
	 * 根据ID删除
	 * */
	int deleteByPrimaryKey(Integer userId);
	/**
	 * 根据姓名删除
	 * */
	int deleteByName(String userName);
	
	/**
	 * 有选择的更新
	 * */
	int updateByPrimaryKeySelective(user record);
}


