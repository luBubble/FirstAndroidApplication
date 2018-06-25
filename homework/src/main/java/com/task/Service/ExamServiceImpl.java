package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Bean.Exam;
import com.task.Dao.ExamDao;

@Service
public class ExamServiceImpl implements ExamService{
	@Autowired
	ExamDao  examDao;
	public void saveExamDao(Exam exam) {
		examDao.saveExamDao(exam);
	}
	
	public Exam selectExamByExam_id(int exam_id) {
		return examDao.selectExamByExam_id(exam_id);
	}
	
	public boolean updateExam(Exam exam) {
		return examDao.updateExam(exam);
	}
	
	public boolean deleteExam(int exam_id) {
		return examDao.deleteExam(exam_id);
	}
	
	public List<Exam> selectExam(){
		List<Exam> allExam = examDao.selectExam();
		return allExam;
	}

	public List<Exam> selectExamByTeacherId(int teacher_id){
		List<Exam> allExam = examDao.selectExamByTeacherId(teacher_id);
		return allExam;
	}
}
