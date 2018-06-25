package com.task.Service;

import java.util.List;

import com.task.Bean.Exam;

public interface ExamService {
void saveExamDao(Exam exam);
	
	public Exam selectExamByExam_id(int exam_id);
	
	boolean updateExam(Exam exam);
	
	boolean deleteExam(int exam_id);
	
	List<Exam> selectExam();
	
	List<Exam> selectExamByTeacherId(int teacher_id);
}
