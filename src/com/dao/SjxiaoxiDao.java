package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjxiaoxiDao extends SqlSessionDaoSupport{
	@Autowired
	private SjxiaoxiMapper sjxiaoxiMapper;

	public List getSjxiaoxiList(Sjxiaoxi record,int page,int rows) {
		List<Sjxiaoxi> list = sjxiaoxiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Sjxiaoxi getSjxiaoxiById(int id){
		Sjxiaoxi sjxiaoxi = sjxiaoxiMapper.selectByPrimaryKey(id);
		
		return sjxiaoxi;
	}

	public void update(Sjxiaoxi sjxiaoxi) {
		sjxiaoxiMapper.updateByPrimaryKey(sjxiaoxi);

	}

	public void delete(Integer id) {
		sjxiaoxiMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjxiaoxi sjxiaoxi) {
		sjxiaoxiMapper.insert(sjxiaoxi);
		
	}
}
