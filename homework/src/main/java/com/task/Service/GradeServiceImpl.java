package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Bean.Grade;
import com.task.Dao.GradeDao;
@Service
public class GradeServiceImpl implements GradeService{
	@Autowired
	GradeDao gradeDao;
	
	public void saveGradeDao(Grade grade) {
		gradeDao.saveGradeDao(grade);
	}
	
	public Grade selectGradeByGrade_id(int grade_id) {
		return gradeDao.selectGradeByGrade_id(grade_id);
	}
	
	public boolean updateGrade(Grade grade) {
		return gradeDao.updateGrade(grade);
	}
	
	public boolean deleteGrade(int grade_id) {
		return gradeDao.deleteGrade(grade_id);
	}
	
	public List<Grade> selectGrade(){
		List<Grade> allGrade = gradeDao.selectGrade();
		return allGrade;
	}
	
	
	public List<Grade> selectGradeByStudentId(int student_id){
		List<Grade> allGrade = gradeDao.selectGradeByStudentId(student_id);
		return allGrade;
	}

	public List<Grade> selectGradeByExamId(int exam_id) {
		List<Grade> allGrade = gradeDao.selectGradeByExamId(exam_id);
		return allGrade;
	}


}
