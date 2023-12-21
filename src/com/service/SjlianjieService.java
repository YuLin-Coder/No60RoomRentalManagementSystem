package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjlianjieService {
	@Autowired
	private SjlianjieDao sjlianjieDao;

	public List querySjlianjies(Sjlianjie record,int page,int rows) {
		// TODO Auto-generated method stub
		return sjlianjieDao.getSjlianjieList(record,page,rows);
	}

	public Sjlianjie getSjlianjie(int parseInt) {
		// TODO Auto-generated method stub
		return sjlianjieDao.getSjlianjieById(parseInt);
	}

	public void modifySjlianjie(Sjlianjie sjlianjie) {
		// TODO Auto-generated method stub
		sjlianjieDao.update(sjlianjie);
	}

	public void deleteSjlianjie(Integer id) {
		// TODO Auto-generated method stub
		sjlianjieDao.delete(id);

	}

	public void save(Sjlianjie sjlianjie) {
		// TODO Auto-generated method stub
		sjlianjieDao.add(sjlianjie);

	}

}
