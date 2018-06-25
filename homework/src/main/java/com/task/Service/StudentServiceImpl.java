package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Bean.Student;
import com.task.Dao.StudentDao;

@Service
public class StudentServiceImpl implements StudentService{
	@Autowired
	StudentDao studentDao;
	public void saveStudentDao(Student stu) {
		studentDao.saveStudentDao(stu);
	}
    
    public Student selectStudentByStudent_id(int student_id) {
    	return studentDao.selectStudentByStudent_id(student_id);
    }
    
    public boolean updateStudent(Student stu) {
    	return studentDao.updateStudent(stu);
    }
    
    public boolean deleteStudent(int student_id) {
    	return studentDao.deleteStudent(student_id);
    }
    
    public List<Student> selectStudent(){
    	List<Student> allStudent = studentDao.selectStudent();
    	return allStudent;
    }
    
    public List<Student> selectStudentByTeacherId(int student_teacherId){
    	List<Student> allStudent = studentDao.selectStudentByTeacherId(student_teacherId);
    	return allStudent;
    }
}
