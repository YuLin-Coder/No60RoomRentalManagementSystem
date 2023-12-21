package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class YxinxiService {
	@Autowired
	private YxinxiDao yxinxiDao;

	public List queryYxinxis(Yxinxi record,int page,int rows, String sdate, String edate) {
		// TODO Auto-generated method stub
		return yxinxiDao.getYxinxiList(record,page,rows,sdate,edate);
	}

	public Yxinxi getYxinxi(int parseInt) {
		// TODO Auto-generated method stub
		return yxinxiDao.getYxinxiById(parseInt);
	}

	public void modifyYxinxi(Yxinxi yxinxi) {
		// TODO Auto-generated method stub
		yxinxiDao.update(yxinxi);
	}

	public void deleteYxinxi(Integer id) {
		// TODO Auto-generated method stub
		yxinxiDao.delete(id);

	}

	public void save(Yxinxi yxinxi) {
		// TODO Auto-generated method stub
		yxinxiDao.add(yxinxi);

	}

}
