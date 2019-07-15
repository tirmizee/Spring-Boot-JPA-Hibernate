package com.tirmizee.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.tirmizee.dto.DeptDTO;
import com.tirmizee.jpa.entities.DemoDept;

@Mapper
public interface DeptMapper extends CommonMapper<DeptDTO, DemoDept> {
	
	static final DeptMapper INSTANCE = Mappers.getMapper(DeptMapper.class);
	
}
