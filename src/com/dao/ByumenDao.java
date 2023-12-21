package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class ByumenDao extends SqlSessionDaoSupport{
	@Autowired
	private ByumenMapper byumenMapper;

	public List getByumenList(Byumen record,int page,int rows) {
		List<Byumen> list = byumenMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Byumen getByumenById(int id){
		Byumen byumen = byumenMapper.selectByPrimaryKey(id);
		
		return byumen;
	}

	public void update(Byumen byumen) {
		byumenMapper.updateByPrimaryKey(byumen);

	}

	public void delete(Integer id) {
		byumenMapper.deleteByPrimaryKey(id);
	}

	public void add(Byumen byumen) {
		byumenMapper.insert(byumen);
		
	}
}
