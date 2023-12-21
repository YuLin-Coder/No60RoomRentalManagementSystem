package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class YxinxiDao extends SqlSessionDaoSupport{
	@Autowired
	private YxinxiMapper yxinxiMapper;

	public List getYxinxiList(Yxinxi record,int page,int rows,String sdate, String edate) {
		List<Yxinxi> list = yxinxiMapper.selectAll(record,page,rows,sdate,edate);
		return list;
	}
	
	public Yxinxi getYxinxiById(int id){
		Yxinxi yxinxi = yxinxiMapper.selectByPrimaryKey(id);
		
		return yxinxi;
	}

	public void update(Yxinxi yxinxi) {
		yxinxiMapper.updateByPrimaryKey(yxinxi);

	}

	public void delete(Integer id) {
		yxinxiMapper.deleteByPrimaryKey(id);
	}

	public void add(Yxinxi yxinxi) {
		yxinxiMapper.insert(yxinxi);
		
	}
}
