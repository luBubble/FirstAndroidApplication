package com.task.Dao;

import java.util.List;

import com.task.Bean.Student;

public interface StudentDao {
	
	void saveStudentDao(Student stu);
    
    public Student selectStudentByStudent_id(int student_id);
    
    boolean updateStudent(Student stu);
    
    boolean deleteStudent(int student_id);
    
    List<Student> selectStudent();
    
    List<Student> selectStudentByTeacherId(int student_teacherId);
}
