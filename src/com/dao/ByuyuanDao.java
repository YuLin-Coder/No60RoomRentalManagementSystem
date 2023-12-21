package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class ByuyuanDao extends SqlSessionDaoSupport{
	@Autowired
	private ByuyuanMapper byuyuanMapper;

	public List getByuyuanList(Byuyuan record,int page,int rows) {
		List<Byuyuan> list = byuyuanMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Byuyuan getByuyuanById(int id){
		Byuyuan byuyuan = byuyuanMapper.selectByPrimaryKey(id);
		
		return byuyuan;
	}

	public void update(Byuyuan byuyuan) {
		byuyuanMapper.updateByPrimaryKey(byuyuan);

	}

	public void delete(Integer id) {
		byuyuanMapper.deleteByPrimaryKey(id);
	}

	public void add(Byuyuan byuyuan) {
		byuyuanMapper.insert(byuyuan);
		
	}
}
