package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class ByuzhiService {
	@Autowired
	private ByuzhiDao byuzhiDao;

	public List queryByuzhis(Byuzhi record,int page,int rows) {
		// TODO Auto-generated method stub
		return byuzhiDao.getByuzhiList(record,page,rows);
	}

	public Byuzhi getByuzhi(int parseInt) {
		// TODO Auto-generated method stub
		return byuzhiDao.getByuzhiById(parseInt);
	}

	public void modifyByuzhi(Byuzhi byuzhi) {
		// TODO Auto-generated method stub
		byuzhiDao.update(byuzhi);
	}

	public void deleteByuzhi(Integer id) {
		// TODO Auto-generated method stub
		byuzhiDao.delete(id);

	}

	public void save(Byuzhi byuzhi) {
		// TODO Auto-generated method stub
		byuzhiDao.add(byuzhi);

	}

}
