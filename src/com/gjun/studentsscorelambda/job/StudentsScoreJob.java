package com.gjun.studentsscorelambda.job;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.gjun.studentsscorelambda.model.StudentsReport;
import com.gjun.studentsscorelambda.service.StudentsScoreService;
import com.gjun.studentsscorelambda.util.SortUtil;

public class StudentsScoreJob {

	public static void main(String[] args) {

		// 準備要讀取的原始csv檔的路徑
		String fileStudentsScoreDataPath = System.getProperty("user.dir") + "/src/com/gjun/studentsscorelambda/StudentsScoreData.csv";
		Path filePath = Paths.get(fileStudentsScoreDataPath);

		// 準備要輸出的經過計算後csv檔的路徑
		String fileStudentsScoreResuletPath = System.getProperty("user.dir") + "/src/com/gjun/studentsscorelambda/StudentsScoreResulet.csv";
		Path targetPath = Paths.get(fileStudentsScoreResuletPath);

		if (Files.exists(filePath)) {// 確認csv檔是否存在

			StudentsReport studentsReport = new StudentsReport();// 用於儲存及傳遞成績單的資料

			StudentsScoreService service = new StudentsScoreService();// 需要呼叫StudentsScoreService這個class的讀取和計算,和輸出的method

			try {

				studentsReport = service.inputStudentsScoreData(filePath);// 讀取並計算每一筆學生成績的總分與平均

				if (studentsReport != null && studentsReport.getStudentsScoreData().size() > 0) {

					SortUtil.sortStudentsScoreByAverage(studentsReport);// 將學生成績進行排序

					service.outputStudentsScoreData(targetPath, studentsReport);// 輸出經過計算後的學生成績單

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
