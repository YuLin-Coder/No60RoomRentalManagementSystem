package com.mapper;

import com.model.Sjjianchu;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SjjianchuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sjjianchu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer sjjianchuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sjjianchu
     *
     * @mbggenerated
     */
    int insert(Sjjianchu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sjjianchu
     *
     * @mbggenerated
     */
    Sjjianchu selectByPrimaryKey(Integer sjjianchuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sjjianchu
     *
     * @mbggenerated
     */
	List<Sjjianchu> selectAll(@Param("sjjianchu")Sjjianchu record,@Param("page")int page,@Param("rows")int rows, @Param("sdate")String sdate, @Param("edate")String edate, @Param("sdate1")String sdate1, @Param("edate1")String edate1);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sjjianchu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Sjjianchu record);
}