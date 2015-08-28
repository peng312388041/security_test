package com.agilet.model;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ProblemEntity implements Serializable {
	// exam_problem_id long
	// exam_problem_content string
	// exam_problem_answers list<exam_answer>
	// exam_problem_score long
	// exam_problem_field1 string
	// exam_problem_field2 string
	// exam_problem_field3 int
	// exam_problem_active boolean

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String key;

//	@Persistent
//	@Extension(vendorName = "datanucleus", key = "gae.pk-name", value = "true")
//	private String keyName;

	// OR:
	@Persistent
	@Extension(vendorName = "datanucleus", key = "gae.pk-id", value = "true")
	private Long keyId;

	@Persistent
	private String content;

	@Persistent
	private List<String> answers;

	@Persistent
	private Long score;

	@Persistent
	private String field1;

	@Persistent
	private String field2;

	@Persistent
	private String field3;

	@Persistent
	private Boolean active;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

//	public String getKeyName() {
//		return keyName;
//	}
//
//	public void setKeyName(String keyName) {
//		this.keyName = keyName;
//	}
	
	
 
	public Long getKeyId() {
		return keyId;
	}

	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
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
}
