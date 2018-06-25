package com.task.Dao;

import java.util.List;

import com.task.Bean.School;

public interface SchoolDao {
	void saveSchoolDao(School school);
	
	public School selectSchoolBySchool_id(int school_id);
	
	boolean updateSchool(School school);
	
	boolean deleteSchool(int school_id);
	
	List<School> selectSchool();
}
