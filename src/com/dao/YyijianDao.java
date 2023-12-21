package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class YyijianDao extends SqlSessionDaoSupport{
	@Autowired
	private YyijianMapper yyijianMapper;

	public List getYyijianList(Yyijian record,int page,int rows,String sdate, String edate) {
		List<Yyijian> list = yyijianMapper.selectAll(record,page,rows,sdate,edate);
		return list;
	}
	
	public Yyijian getYyijianById(int id){
		Yyijian yyijian = yyijianMapper.selectByPrimaryKey(id);
		
		return yyijian;
	}

	public void update(Yyijian yyijian) {
		yyijianMapper.updateByPrimaryKey(yyijian);

	}

	public void delete(Integer id) {
		yyijianMapper.deleteByPrimaryKey(id);
	}

	public void add(Yyijian yyijian) {
		yyijianMapper.insert(yyijian);
		
	}
}
