package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class YhroleDao extends SqlSessionDaoSupport{
	@Autowired
	private YhroleMapper yhroleMapper;

	public List getYhroleList(Yhrole record,int page,int rows) {
		List<Yhrole> list = yhroleMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Yhrole getYhroleById(int id){
		Yhrole yhrole = yhroleMapper.selectByPrimaryKey(id);
		
		return yhrole;
	}

	public void update(Yhrole yhrole) {
		yhroleMapper.updateByPrimaryKey(yhrole);

	}

	public void delete(Integer id) {
		yhroleMapper.deleteByPrimaryKey(id);
	}

	public void add(Yhrole yhrole) {
		yhroleMapper.insert(yhrole);
		
	}
}
