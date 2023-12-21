package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjxiaoxiService {
	@Autowired
	private SjxiaoxiDao sjxiaoxiDao;

	public List querySjxiaoxis(Sjxiaoxi record,int page,int rows) {
		// TODO Auto-generated method stub
		return sjxiaoxiDao.getSjxiaoxiList(record,page,rows);
	}

	public Sjxiaoxi getSjxiaoxi(int parseInt) {
		// TODO Auto-generated method stub
		return sjxiaoxiDao.getSjxiaoxiById(parseInt);
	}

	public void modifySjxiaoxi(Sjxiaoxi sjxiaoxi) {
		// TODO Auto-generated method stub
		sjxiaoxiDao.update(sjxiaoxi);
	}

	public void deleteSjxiaoxi(Integer id) {
		// TODO Auto-generated method stub
		sjxiaoxiDao.delete(id);

	}

	public void save(Sjxiaoxi sjxiaoxi) {
		// TODO Auto-generated method stub
		sjxiaoxiDao.add(sjxiaoxi);

	}

}
