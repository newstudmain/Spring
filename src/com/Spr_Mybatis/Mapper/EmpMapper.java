package com.Spr_Mybatis.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.Spr_Mybatis.bean.Emp;

public interface EmpMapper {
	
	@Select("select id,last_name lastName,first_name firstName,salary,start_date startDate from s_emp where id=#{id}")
	public List<Emp> selectAll(Integer id);
}
