package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class YxtypeService {
	@Autowired
	private YxtypeDao yxtypeDao;

	public List queryYxtypes(Yxtype record,int page,int rows) {
		// TODO Auto-generated method stub
		return yxtypeDao.getYxtypeList(record,page,rows);
	}

	public Yxtype getYxtype(int parseInt) {
		// TODO Auto-generated method stub
		return yxtypeDao.getYxtypeById(parseInt);
	}

	public void modifyYxtype(Yxtype yxtype) {
		// TODO Auto-generated method stub
		yxtypeDao.update(yxtype);
	}

	public void deleteYxtype(Integer id) {
		// TODO Auto-generated method stub
		yxtypeDao.delete(id);

	}

	public void save(Yxtype yxtype) {
		// TODO Auto-generated method stub
		yxtypeDao.add(yxtype);

	}

}
