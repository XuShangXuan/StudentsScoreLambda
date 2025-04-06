package com.gjun.studentsscorelambda.model;

import java.util.List;

public class StudentsReport {

	private String reportTitle;

	private List<StudentsScore> studentsScoreData;

	public String getReportTitle() {
		return reportTitle + ",總分,平均";
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public List<StudentsScore> getStudentsScoreData() {
		return studentsScoreData;
	}

	public void setStudentsScoreData(List<StudentsScore> studentsScoreData) {
		this.studentsScoreData = studentsScoreData;
	}

}
