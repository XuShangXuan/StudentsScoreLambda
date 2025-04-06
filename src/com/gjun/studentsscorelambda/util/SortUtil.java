package com.gjun.studentsscorelambda.util;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.gjun.studentsscorelambda.model.StudentsReport;
import com.gjun.studentsscorelambda.model.StudentsScore;

public class SortUtil {

	public static void sortStudentsScoreByAverage(StudentsReport studentsReport) {

		Function<StudentsScore, String> sumScoreSort = StudentsScore::getSumScore;
		Function<StudentsScore, String> chineseSort = s -> s.getScore().get(0);
		Function<StudentsScore, String> mathSort = s -> s.getScore().get(2);

		Comparator<StudentsScore> comp = Comparator.comparing(sumScoreSort)
				.thenComparing(chineseSort)
				.thenComparing(mathSort);

		studentsReport.setStudentsScoreData(
				studentsReport.getStudentsScoreData().stream()
				.sorted(comp.reversed())
				.collect(Collectors.toList()));

	}

}
