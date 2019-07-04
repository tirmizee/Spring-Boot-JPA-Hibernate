package com.tirmizee.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Dept {

	@Id
	@Setter @Getter 
	private Integer deptno;
	
	@Setter @Getter 
	private String dname;
	
	@Setter @Getter 
	private String loc;
	
}
