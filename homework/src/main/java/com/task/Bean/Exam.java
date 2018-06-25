package com.task.Bean;

import java.util.Date;

public class Exam {
	private int Exam_id;
	private String Exam_name;
	private int Exam_teacherId;
	private Date Exam_time;
	public int getExam_id() {
		return Exam_id;
	}
	public void setExam_id(int exam_id) {
		Exam_id = exam_id;
	}
	public String getExam_name() {
		return Exam_name;
	}
	public void setExam_name(String exam_name) {
		Exam_name = exam_name;
	}
	public int getExam_teacherId() {
		return Exam_teacherId;
	}
	public void setExam_teacherId(int exam_teacherId) {
		Exam_teacherId = exam_teacherId;
	}
	public Date getExam_time() {
		return Exam_time;
	}
	public void setExam_time(Date exam_time) {
		Exam_time = exam_time;
	}
}
