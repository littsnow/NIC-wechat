package com.litt.nic.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.litt.nic.pojo.techsupport;

public interface techsupportMapper {
	int deleteByPrimaryKey(Integer techsupportId);

    int insert(techsupport record);

    int insertSelective(techsupport record);

    techsupport selectByPrimaryKey(Integer techsupportId);

    int updateByPrimaryKeySelective(techsupport record);

    int updateByPrimaryKey(techsupport record);
    List<techsupport> selectAllTS();
    List<techsupport> selectTSMultiInfo(@Param("key") String key,@Param("val") String val);
}