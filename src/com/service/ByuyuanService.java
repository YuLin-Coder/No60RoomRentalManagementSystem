package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class ByuyuanService {
	@Autowired
	private ByuyuanDao byuyuanDao;

	public List queryByuyuans(Byuyuan record,int page,int rows) {
		// TODO Auto-generated method stub
		return byuyuanDao.getByuyuanList(record,page,rows);
	}

	public Byuyuan getByuyuan(int parseInt) {
		// TODO Auto-generated method stub
		return byuyuanDao.getByuyuanById(parseInt);
	}

	public void modifyByuyuan(Byuyuan byuyuan) {
		// TODO Auto-generated method stub
		byuyuanDao.update(byuyuan);
	}

	public void deleteByuyuan(Integer id) {
		// TODO Auto-generated method stub
		byuyuanDao.delete(id);

	}

	public void save(Byuyuan byuyuan) {
		// TODO Auto-generated method stub
		byuyuanDao.add(byuyuan);

	}

}
