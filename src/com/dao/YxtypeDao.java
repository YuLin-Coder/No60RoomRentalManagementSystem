package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class YxtypeDao extends SqlSessionDaoSupport{
	@Autowired
	private YxtypeMapper yxtypeMapper;

	public List getYxtypeList(Yxtype record,int page,int rows) {
		List<Yxtype> list = yxtypeMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Yxtype getYxtypeById(int id){
		Yxtype yxtype = yxtypeMapper.selectByPrimaryKey(id);
		
		return yxtype;
	}

	public void update(Yxtype yxtype) {
		yxtypeMapper.updateByPrimaryKey(yxtype);

	}

	public void delete(Integer id) {
		yxtypeMapper.deleteByPrimaryKey(id);
	}

	public void add(Yxtype yxtype) {
		yxtypeMapper.insert(yxtype);
		
	}
}
