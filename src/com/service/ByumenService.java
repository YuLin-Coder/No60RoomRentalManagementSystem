package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class ByumenService {
	@Autowired
	private ByumenDao byumenDao;

	public List queryByumens(Byumen record,int page,int rows) {
		// TODO Auto-generated method stub
		return byumenDao.getByumenList(record,page,rows);
	}

	public Byumen getByumen(int parseInt) {
		// TODO Auto-generated method stub
		return byumenDao.getByumenById(parseInt);
	}

	public void modifyByumen(Byumen byumen) {
		// TODO Auto-generated method stub
		byumenDao.update(byumen);
	}

	public void deleteByumen(Integer id) {
		// TODO Auto-generated method stub
		byumenDao.delete(id);

	}

	public void save(Byumen byumen) {
		// TODO Auto-generated method stub
		byumenDao.add(byumen);

	}

}
