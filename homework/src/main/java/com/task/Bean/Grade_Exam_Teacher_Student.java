package com.task.Bean;

import java.util.Date;

public class Grade_Exam_Teacher_Student {
	private int Student_id;
	private String Student_name;
	private String Exam_name;
	private Date Exam_time;
	private String Teacher_name;
	private String Student_class;
	private int Grade_num;
	public int getStudent_id() {
		return Student_id;
	}
	public void setStudent_id(int student_id) {
		Student_id = student_id;
	}
	public String getStudent_name() {
		return Student_name;
	}
	public void setStudent_name(String student_name) {
		Student_name = student_name;
	}
	public String getExam_name() {
		return Exam_name;
	}
	public void setExam_name(String exam_name) {
		Exam_name = exam_name;
	}
	public Date getExam_time() {
		return Exam_time;
	}
	public void setExam_time(Date exam_time) {
		Exam_time = exam_time;
	}

	public String getTeacher_name() {
		return Teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		Teacher_name = teacher_name;
	}
	public String getStudent_class() {
		return Student_class;
	}
	public void setStudent_class(String student_class) {
		Student_class = student_class;
	}
	public int getGrade_num() {
		return Grade_num;
	}
	public void setGrade_num(int grade_num) {
		Grade_num = grade_num;
	}
	
	public static Grade_Exam_Teacher_Student connect(Grade grade,Exam exam,Teacher teacher,Student student) {
		Grade_Exam_Teacher_Student con = new Grade_Exam_Teacher_Student();
		con.setStudent_id(student.getStudent_id());
		con.setStudent_name(student.getStudent_name());
		con.setExam_name(exam.getExam_name());
		con.setExam_time(exam.getExam_time());
		con.setStudent_class(student.getStudent_class());
		con.setTeacher_name(teacher.getTeacher_name());
		con.setGrade_num(grade.getGrade_num());
		
		return con;
	}
}
