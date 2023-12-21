package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class YhroleService {
	@Autowired
	private YhroleDao yhroleDao;

	public List queryYhroles(Yhrole record,int page,int rows) {
		// TODO Auto-generated method stub
		return yhroleDao.getYhroleList(record,page,rows);
	}

	public Yhrole getYhrole(int parseInt) {
		// TODO Auto-generated method stub
		return yhroleDao.getYhroleById(parseInt);
	}

	public void modifyYhrole(Yhrole yhrole) {
		// TODO Auto-generated method stub
		yhroleDao.update(yhrole);
	}

	public void deleteYhrole(Integer id) {
		// TODO Auto-generated method stub
		yhroleDao.delete(id);

	}

	public void save(Yhrole yhrole) {
		// TODO Auto-generated method stub
		yhroleDao.add(yhrole);

	}

}
