package com.litt.nic.ServiceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litt.nic.Dao.userMapper;
import com.litt.nic.Service.userService;
import com.litt.nic.pojo.user;

@Service("userService")
public class userServiceImpl implements userService {

	@Autowired
	private userMapper usermapper;

	/**
	 * 查找
	 */
	// 根据ID查找
	@Override
	public user selectByPrimaryKey(Integer userId) {
		return usermapper.selectByPrimaryKey(userId);
	}
	// 根据姓名查找
	@Override
	public user selectByName(String userName) {
		return usermapper.selectByName(userName);
	}
	//多条件查找用户
	@Override
	public List<user> selectByCondition(user record) {
		// TODO Auto-generated method stub
		return usermapper.selectByCondition(record);
	}
	//多条件模糊查询返回单条信息
	@Override
	public user selectByConditionOne(user record) {
		return usermapper.selectByConditionOne(record);
	}
	//查出全部用户
	@Override
	public List<user> selectAllUser() {
		// TODO Auto-generated method stub
		return usermapper.selectAllUser();
	}
	
	/**
	 * 插入
	 */
	// 全部插入
	@Override
	public int insert(user record) {
		return usermapper.insert(record);
	}
	// 有选择的插入
//	@Override
//	public int insertSelective(user record) {
//		return usermapper.insertSelective(record);
//	}

	/**
	 * 删除
	 */
	// 按ID删除
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		return usermapper.deleteByPrimaryKey(userId);
	}

	// 按姓名删除
	@Override
	public int deleteByName(String userName) {
		return usermapper.deleteByName(userName);
	}

	/**
	 * 更新
	 */
	// 有选择的更新
	@Override
	public int updateByPrimaryKeySelective(user record) {
		return usermapper.updateByPrimaryKeySelective(record);
	}
	
	
	
	

}
