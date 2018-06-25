package com.task.Service;

import java.util.List;

import com.task.Bean.Relation;

public interface RelationService {
	void saveRelationDao();
	
	public Relation selectRelationByRelation_id(int relation_id);
	
	boolean updateRelation(Relation relation);
	
	boolean deleteRelation(int relation_id);
	
	List<Relation> selectRelation();
	
	List<Relation> selectRelationByTeacher_id(int teacher_id);//根据老师id查找
	
	List<Relation> selectRelationByStudent_id(int student_id);//根据老师id查找
}
