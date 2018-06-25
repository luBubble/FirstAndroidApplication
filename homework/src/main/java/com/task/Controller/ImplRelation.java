package com.task.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.task.Bean.Relation;
import com.task.Service.RelationService;

@Controller
@RequestMapping("/Relation")
public class ImplRelation {
	@Autowired
    private RelationService relationService;
	
	@RequestMapping("/StudentIdentify")//ѧ������ʦ
	public void StudentIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String stu_id = request.getParameter("student_id");//��ȡѧ��id
		String tea_id = request.getParameter("teacher_id");//��ȡ��ʦid
		
		Relation re = new Relation();
		re.setRelation_state(1);
		re.setRelation_status(1);
		re.setRelation_studentId(Integer.parseInt(stu_id));
		re.setRelation_teacherId(Integer.parseInt(tea_id));
		response.getWriter().write("1");
	}
	
	@RequestMapping("/TeacherIdentify")//��ʦ��ѧ��
	public void TeacherIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String stu_id = request.getParameter("student_id");//��ȡѧ��id
		String tea_id = request.getParameter("teacher_id");//��ȡ��ʦid
		
		Relation re = new Relation();
		re.setRelation_state(1);
		re.setRelation_status(2);
		re.setRelation_studentId(Integer.parseInt(stu_id));
		re.setRelation_teacherId(Integer.parseInt(tea_id));
		response.getWriter().write("1");
	}
	
	@RequestMapping("/SuccessIdentify")//���ͨ��
	public void SuccessIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rel_id = request.getParameter("relation_id");//��ȡ��id
		
		Relation re = new Relation();
		re = relationService.selectRelationByRelation_id(Integer.parseInt(rel_id));
		re.setRelation_state(2);
		response.getWriter().write("1");
	}
	@RequestMapping("/DefeatIdentify")//��˾ܾ�
	public void DefeatIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rel_id = request.getParameter("relation_id");//��ȡ��id
		
		Relation re = new Relation();
		re = relationService.selectRelationByRelation_id(Integer.parseInt(rel_id));
		re.setRelation_state(3);
		response.getWriter().write("1");
	}
	@RequestMapping("/CancelIdentify")//�����
	public void CancelIdentify(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rel_id = request.getParameter("relation_id");//��ȡ��id
		
		Relation re = new Relation();
		re = relationService.selectRelationByRelation_id(Integer.parseInt(rel_id));
		re.setRelation_state(4);
		response.getWriter().write("1");
	}
	
	
	@RequestMapping("/searchIdentifyByTeacher_id/{id}")//������ʦ������֤��Ϣ
	public ResponseEntity<List<Relation>> searchIdentifyByTeacher_id(@PathVariable int id){
		List<Relation> Identify = relationService.selectRelationByTeacher_id(id);
		return new ResponseEntity<List<Relation>>(Identify, HttpStatus.OK);
	}
	
	@RequestMapping("/searchIdentifyByStudent_id/{id}")//����ѧ��������֤��Ϣ
	public ResponseEntity<List<Relation>> searchIdentifyByStudent_id(@PathVariable int id){
		List<Relation> Identify = relationService.selectRelationByStudent_id(id);
		return new ResponseEntity<List<Relation>>(Identify, HttpStatus.OK);
	}
}
