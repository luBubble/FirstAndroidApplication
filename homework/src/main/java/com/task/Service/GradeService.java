package com.task.Service;

import java.util.List;

import com.task.Bean.Grade;
import com.task.Dao.GradeDao;

public interface GradeService {
	void saveGradeDao(Grade grade);
	
	public Grade selectGradeByGrade_id(int grade_id);
	
	boolean updateGrade(Grade grade);
	
	boolean deleteGrade(int grade_id);
	
	List<Grade> selectGrade();
	
	List<Grade> selectGradeByStudentId(int student_id);
	
	List<Grade> selectGradeByExamId(int exam_id);
}
