package com.task.Service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.task.BaseTest.SpringTestCase;
import com.task.Bean.Grade;
import com.task.Bean.Student;
import com.task.Bean.Teacher;

public class UserServiceTest extends SpringTestCase{
	@Autowired  
    private TeacherService teacherService;
	@Autowired  
    private GradeService gradeService;
	
	@Test
	public void save() {
		List<Grade> grade = gradeService.selectGradeByStudentId(1);
		System.out.println(grade.get(0).getGrade_wrong());
	}
}
