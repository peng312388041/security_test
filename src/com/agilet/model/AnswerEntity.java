package com.agilet.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class AnswerEntity {
	// exam_answer_id long
	// exam_answer_content string
	// exam_answer_result boolean
	// exam_answer_field1 string
	// exam_answer_field2 string
	// exam_answer_field3 int
	// exam_answer_active boolean

	@PrimaryKey
	private String key;
	@Persistent
	private String content;
	@Persistent
	private boolean result;
	@Persistent
	private String field1;
	@Persistent
	private String field2;
	@Persistent
	private Integer field3;
	@Persistent
	private boolean active;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public Integer getField3() {
		return field3;
	}

	public void setField3(Integer field3) {
		this.field3 = field3;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
