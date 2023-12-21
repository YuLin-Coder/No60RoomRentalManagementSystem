package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjlianjieDao extends SqlSessionDaoSupport{
	@Autowired
	private SjlianjieMapper sjlianjieMapper;

	public List getSjlianjieList(Sjlianjie record,int page,int rows) {
		List<Sjlianjie> list = sjlianjieMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Sjlianjie getSjlianjieById(int id){
		Sjlianjie sjlianjie = sjlianjieMapper.selectByPrimaryKey(id);
		
		return sjlianjie;
	}

	public void update(Sjlianjie sjlianjie) {
		sjlianjieMapper.updateByPrimaryKey(sjlianjie);

	}

	public void delete(Integer id) {
		sjlianjieMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjlianjie sjlianjie) {
		sjlianjieMapper.insert(sjlianjie);
		
	}
}
