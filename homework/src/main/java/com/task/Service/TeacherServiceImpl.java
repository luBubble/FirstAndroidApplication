package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Bean.Teacher;
import com.task.Dao.TeacherDao;

@Service
public class TeacherServiceImpl implements TeacherService{
	@Autowired
	TeacherDao teacherDao;  
	
	public  void saveTeacherDao(Teacher tea){
		teacherDao.saveTeacherDao(tea);
	}
	public Teacher selectTeacherByTeacher_id(int teacher_id) {
		return teacherDao.selectTeacherByTeacher_id(teacher_id);
	}
	public boolean updateTeacher(Teacher tea) {
		return teacherDao.updateTeacher(tea);
	}
	
	public boolean updateTeacherPassword(Teacher tea) {
		return teacherDao.updateTeacherPassword(tea);
	}
	public boolean deleteTeacher(int teacher_id) {
		return teacherDao.deleteTeacher(teacher_id);
	}
	
	public List<Teacher> selectTeacher(){
		List<Teacher> allTeacher = teacherDao.selectTeacher();
		return allTeacher;
	}
	public Teacher selectTeacherByTeacher_account(String teacher_account) {
		// TODO Auto-generated method stub
		return teacherDao.selectTeacherByTeacher_account(teacher_account);
	}
}
