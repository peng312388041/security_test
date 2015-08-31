package com.agilet.dto;

import java.io.Serializable;

import com.agilet.model.ProblemEntity;

public class Question extends ProblemEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String problemKey;
	Integer id;
 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
