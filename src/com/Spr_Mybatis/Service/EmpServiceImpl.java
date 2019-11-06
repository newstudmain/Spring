package com.Spr_Mybatis.Service;

import java.util.List;

import com.Spr_Mybatis.Mapper.EmpMapper;
import com.Spr_Mybatis.bean.Emp;

public class EmpServiceImpl implements EmpService{

	private EmpMapper empMapper;
	
	@Override
	public List<Emp> show() {
		return empMapper.selectAll(12);
	}

	public EmpMapper getEmpMapper() {
		return empMapper;
	}

	public void setEmpMapper(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}

}
