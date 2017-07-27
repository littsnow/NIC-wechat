package com.litt.nic.service;

import java.util.List;

import com.litt.nic.pojo.maintenance;

public interface IMainTenanceService {
  List<maintenance> findAllMT();
  List<maintenance> findByMutiInfo(String key,String val);
}
