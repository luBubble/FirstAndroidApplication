package com.task.Service;

import java.util.List;

import com.task.Bean.Teacher;

public interface TeacherService {
void saveTeacherDao(Teacher tea);
	
	public Teacher selectTeacherByTeacher_id(int teacher_id);
	
	public Teacher selectTeacherByTeacher_account(String teacher_account);
	
	boolean updateTeacher(Teacher tea);
	
	boolean updateTeacherPassword(Teacher tea);
	
	boolean deleteTeacher(int teacher_id);
	
	List<Teacher> selectTeacher();
}
