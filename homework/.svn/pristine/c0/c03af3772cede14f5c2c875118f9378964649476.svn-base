package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Bean.School;
import com.task.Dao.SchoolDao;

@Service
public class SchoolServiceImpl implements SchoolService{
	@Autowired
	SchoolDao schoolDao;
	public void saveSchoolDao(School school) {
		schoolDao.saveSchoolDao(school);
	}
	
	public School selectSchoolBySchool_id(int school_id) {
		return schoolDao.selectSchoolBySchool_id(school_id);
	}
	
	public boolean updateSchool(School school) {
		return schoolDao.updateSchool(school);
	}
	
	public boolean deleteSchool(int school_id) {
		return schoolDao.deleteSchool(school_id);
	}
	
	public List<School> selectSchool(){
		List<School> allSchool = schoolDao.selectSchool();
		return allSchool;
	}
}
