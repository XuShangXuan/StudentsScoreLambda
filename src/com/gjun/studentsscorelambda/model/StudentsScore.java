package com.gjun.studentsscorelambda.model;

import java.util.List;

public class StudentsScore {
	// 座號
	private String studentNo;
	// 姓名
	private String name;
	// 各科分數
	private List<String> score;
	// 總分
	private String sumScore;
	// 平均
	private String average;

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getScore() {
		return score;
	}

	public void setScore(List<String> score) {
		this.score = score;
	}

	public String getSumScore() {
		return sumScore;
	}

	public void setSumScore(String sumScore) {
		this.sumScore = sumScore;
	}

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}
	
}
