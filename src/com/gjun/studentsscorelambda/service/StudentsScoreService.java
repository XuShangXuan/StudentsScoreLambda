package com.gjun.studentsscorelambda.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.gjun.studentsscorelambda.model.StudentsReport;
import com.gjun.studentsscorelambda.model.StudentsScore;

public class StudentsScoreService {

	public StudentsReport inputStudentsScoreData(Path filePath) throws IOException {

		StudentsReport studentsReport = new StudentsReport();
		List<StudentsScore> studentsScores = new ArrayList();

		Charset cs = Charset.defaultCharset();// 讀取檔案的格式，預設是UTF-8

		List<String> lines = Files.readAllLines(filePath, cs);// 讀取的檔案資料內容，一行行的寫入List(讀進來的內容，型態一定是String)

		studentsReport.setReportTitle(lines.get(0));// 先將資料內容的第一行(title)存入StudentsReport

		for (int i = 1; i < lines.size(); i++) {

			StudentsScore stu = new StudentsScore();// 從第二行開始，每一行的都用一個學生物件存取資料
			String[] column = lines.get(i).split(",");// 利用csv用 "," 將資料作分隔的特性，將每一欄的資料分開

			stu.setStudentNo(column[0]);// 第一欄的資料一定是座號，存入StudentsScore的"座號"欄位中
			stu.setName(column[1]);// 第二欄的資料一定是名稱，存入StudentsScore的"姓名"欄位中

			List<String> scoreData = new ArrayList<>();

			for (int j = 2; j < column.length; j++) { // 從第三欄開始會是各科成績

				scoreData.add(column[j]);// 將每一科成績依序加入List中

			}

			stu.setScore(scoreData);// 將成績存入StudentsScore的"成績"欄位中
			
			// 計算總成績並存入StudentsScore的"總成績"欄位中
			stu.setSumScore(String.valueOf(
					scoreData.stream()
					.mapToInt(s -> Integer.parseInt(s))
					.sum()));

			// 得到平均成績
			double avg = scoreData.stream()
					.mapToDouble(s -> Double.parseDouble(s))
					.average()
					.getAsDouble();
			
			avg = Math.round(avg * 10.0) / 10.0;// 平均成績算到小數點後第一位
			
			stu.setAverage(String.valueOf(avg));// 將平均成績存入StudentsScore的"平均成績"欄位中

			// 將所有資料存入StudentsReport
			studentsScores.add(stu);
			studentsReport.setStudentsScoreData(studentsScores);

		}

		return studentsReport;
	}

	public void outputStudentsScoreData(Path targetPath, StudentsReport studentsReport) throws IOException {

		List<StringBuilder> lines = new ArrayList<>();// 輸出的資料只允許是集合<裡面放家族>的型態

		// Title的資料要另外處理，並優先寫入
		StringBuilder title = new StringBuilder(studentsReport.getReportTitle());
		lines.add(title);

		// 一行一行將學生成績存入List中
		for (int i = 0; i < studentsReport.getStudentsScoreData().size(); i++) {

			StringBuilder line = new StringBuilder();// 將每一欄資料串接起來
			line.append(studentsReport.getStudentsScoreData().get(i).getStudentNo());// 串入座號
			line.append(",");// 每一欄資料根據csv檔的特性，使用","隔開
			line.append(studentsReport.getStudentsScoreData().get(i).getName());// 串入名稱
			line.append(",");
			line.append(studentsReport.getStudentsScoreData().get(i).getScore().stream()
					.collect(Collectors.joining(","))); //串入各科成績
			line.append(",");
			line.append(studentsReport.getStudentsScoreData().get(i).getSumScore());// 串入總成績
			line.append(",");
			line.append(studentsReport.getStudentsScoreData().get(i).getAverage());// 串入平均成績

			lines.add(line);

		}

		Charset cs = Charset.defaultCharset();// 輸出格式

		Files.write(targetPath, lines, cs, StandardOpenOption.CREATE);// 輸出學生成績的檔案

	}

}
