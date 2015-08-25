package com.agilet.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TestEntity implements Serializable{
	// exam_test_id long 考试id
	// exam_test_name string 考试名称
	// exam_test_begin_date date 考试开始日期
	// exam_test_end_date date 考试结束日期
	// exam_test_total_user_testes List<exam_user_test> 所有用户考试信息
	// exam_test_total_time int 考试总时间
	// exam_test_user_field1 string 备用字段1
	// exam_test_user_field2 string 备用字段2
	// exam_test_user_field3 long 备用字段3
	// exam_test_active boolean 活动标记(已废除为false，否则为true)

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String name;

	@Persistent
	private long beginDate;

	@Persistent
	private long endDate;

	@Persistent
	private List<String> userTestes;

	@Persistent
	private Integer totalTime;

	@Persistent
	private String field1;

	@Persistent
	private String field2;

	@Persistent
	private String field3;

	@Persistent
	private Boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(long beginDate) {
		this.beginDate = beginDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public Integer getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
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

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<String> getUserTestes() {
		return userTestes;
	}

	public void setUserTestes(List<String> userTestes) {
		this.userTestes = userTestes;
	}

}
