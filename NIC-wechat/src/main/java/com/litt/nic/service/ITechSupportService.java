package com.litt.nic.service;

import java.util.List;

import com.litt.nic.Dao.techsupportMapper;
import com.litt.nic.pojo.techsupport;

public interface ITechSupportService {
     List<techsupport> findAllTS();
     List<techsupport> findByMutilInfo(String key,String val);
}
