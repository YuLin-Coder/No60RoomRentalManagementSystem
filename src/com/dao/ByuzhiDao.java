package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class ByuzhiDao extends SqlSessionDaoSupport{
	@Autowired
	private ByuzhiMapper byuzhiMapper;

	public List getByuzhiList(Byuzhi record,int page,int rows) {
		List<Byuzhi> list = byuzhiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Byuzhi getByuzhiById(int id){
		Byuzhi byuzhi = byuzhiMapper.selectByPrimaryKey(id);
		
		return byuzhi;
	}

	public void update(Byuzhi byuzhi) {
		byuzhiMapper.updateByPrimaryKey(byuzhi);

	}

	public void delete(Integer id) {
		byuzhiMapper.deleteByPrimaryKey(id);
	}

	public void add(Byuzhi byuzhi) {
		byuzhiMapper.insert(byuzhi);
		
	}
}
