package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class YyijianService {
	@Autowired
	private YyijianDao yyijianDao;

	public List queryYyijians(Yyijian record,int page,int rows, String sdate, String edate) {
		// TODO Auto-generated method stub
		return yyijianDao.getYyijianList(record,page,rows,sdate,edate);
	}

	public Yyijian getYyijian(int parseInt) {
		// TODO Auto-generated method stub
		return yyijianDao.getYyijianById(parseInt);
	}

	public void modifyYyijian(Yyijian yyijian) {
		// TODO Auto-generated method stub
		yyijianDao.update(yyijian);
	}

	public void deleteYyijian(Integer id) {
		// TODO Auto-generated method stub
		yyijianDao.delete(id);

	}

	public void save(Yyijian yyijian) {
		// TODO Auto-generated method stub
		yyijianDao.add(yyijian);

	}

}
