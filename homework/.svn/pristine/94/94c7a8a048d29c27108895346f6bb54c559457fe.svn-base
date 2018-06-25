package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Bean.Relation;
import com.task.Dao.RelationDao;

@Service
public class RelationServiceImpl implements RelationService{
	@Autowired
	RelationDao relationDao;
	public void saveRelationDao() {
		relationDao.saveRelationDao();
	}
	
	public Relation selectRelationByRelation_id(int relation_id) {
		return relationDao.selectRelationByRelation_id(relation_id);
	}
	
	public boolean updateRelation(Relation relation) {
		return relationDao.updateRelation(relation);
	}
	
	public boolean deleteRelation(int relation_id) {
		return relationDao.deleteRelation(relation_id);
	}
	
	public List<Relation> selectRelation(){
		List<Relation> allRelation = relationDao.selectRelation();
		return allRelation;
	}
	
	public List<Relation> selectRelationByTeacher_id(int teacher_id){//根据老师id查找
		List<Relation> RelationBytea_id = relationDao.selectRelationByTeacher_id(teacher_id);
		return RelationBytea_id;
	}
	
	public List<Relation> selectRelationByStudent_id(int student_id){//根据老师id查找
		List<Relation> RelationBystu_id = relationDao.selectRelationByStudent_id(student_id);
		return RelationBystu_id;
	}
}
