package com.task.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.task.Bean.Grade;
import com.task.Bean.Grade_Exam_Teacher_Student;
import com.task.Bean.Student;
import com.task.Bean.Teacher;
import com.task.Service.ExamService;
import com.task.Service.GradeService;
import com.task.Service.StudentService;
import com.task.Service.TeacherService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/Student")
public class StudentMsgController {
	
	@Autowired
    private StudentService studentService;
	
	@Autowired
    private TeacherService teacherService;
	@Autowired
    private ExamService examService;
	@Autowired
    private GradeService gradeService;
	
	
	
	@RequestMapping("/allTeacher")
	public ResponseEntity<JSONObject> listTeachers(@RequestParam String aoData,HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		String sEcho = null;
	    int iDisplayStart = 0; // ��ʼ����
	    int iDisplayLength = 0; // ÿҳ��ʾ������
	 
	    for (int i = 0; i < jsonarray.size(); i++) {
	        JSONObject obj = (JSONObject) jsonarray.get(i);
	        if (obj.get("name").equals("sEcho"))
	            sEcho = obj.get("value").toString();
	 
	        if (obj.get("name").equals("iDisplayStart"))
	            iDisplayStart = obj.getInt("value");
	 
	        if (obj.get("name").equals("iDisplayLength"))
	            iDisplayLength = obj.getInt("value");
	        
	    }
	    List<Teacher> conlist=teacherService.selectTeacher();
	    System.out.println("--------------");
		JSONObject getObj = new JSONObject();
	    getObj.put("sEcho", sEcho);// DataTable������Ⱦ����Ϣ
	    getObj.put("iTotalRecords", conlist.size());//ȫ�����ݵ�����
	    getObj.put("iTotalDisplayRecords", conlist.size());//��ʾ������  
	    if(iDisplayStart+iDisplayLength<conlist.size())
	    {
	    	getObj.put("data", conlist.subList(iDisplayStart, iDisplayStart+iDisplayLength));//Ҫ��JSON��ʽ����
	    }
	    else
	    {
	    	getObj.put("data", conlist.subList(iDisplayStart, conlist.size()));//Ҫ��JSON��ʽ����
	    }
		
	    return new ResponseEntity<JSONObject>(getObj, HttpStatus.OK);
	}
	
	@RequestMapping("/allStudentsOfTeacher")
	public ResponseEntity<JSONObject> listStudents(@RequestParam String teacher_id,@RequestParam String aoData,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		String sEcho = null;
		int teacher_id1 =Integer.parseInt(teacher_id);
	    int iDisplayStart = 0; // ��ʼ����
	    int iDisplayLength = 0; // ÿҳ��ʾ������
	 
	    for (int i = 0; i < jsonarray.size(); i++) {
	        JSONObject obj = (JSONObject) jsonarray.get(i);
	        if (obj.get("name").equals("sEcho"))
	            sEcho = obj.get("value").toString();
	 
	        if (obj.get("name").equals("iDisplayStart"))
	            iDisplayStart = obj.getInt("value");
	 
	        if (obj.get("name").equals("iDisplayLength"))
	            iDisplayLength = obj.getInt("value");
	        
	    }
	    List<Student> conlist=studentService.selectStudentByTeacherId(teacher_id1);
	    System.out.println("--------------");
		JSONObject getObj = new JSONObject();
	    getObj.put("sEcho", sEcho);// DataTable������Ⱦ����Ϣ
	    getObj.put("iTotalRecords", conlist.size());//ȫ�����ݵ�����
	    getObj.put("iTotalDisplayRecords", conlist.size());//��ʾ������  
	    if(iDisplayStart+iDisplayLength<conlist.size())
	    {
	    	getObj.put("data", conlist.subList(iDisplayStart, iDisplayStart+iDisplayLength));//Ҫ��JSON��ʽ����
	    }
	    else
	    {
	    	getObj.put("data", conlist.subList(iDisplayStart, conlist.size()));//Ҫ��JSON��ʽ����
	    }
		
	    return new ResponseEntity<JSONObject>(getObj, HttpStatus.OK);
	}
	
	
	
//	@RequestMapping("/StudentGrade") //ѧ�����гɼ�
//	public ResponseEntity<JSONObject> StudentGrade(@RequestParam String  student_id,@RequestParam String aoData,HttpServletResponse response) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		JSONArray jsonarray = JSONArray.fromObject(aoData);
//		String sEcho = null;
//		int student_id1 =Integer.parseInt(student_id);
//		System.out.println(student_id1);
//	    int iDisplayStart = 0; // ��ʼ����
//	    int iDisplayLength = 0; // ÿҳ��ʾ������
//	 
//	    for (int i = 0; i < jsonarray.size(); i++) {
//	        JSONObject obj = (JSONObject) jsonarray.get(i);
//	        if (obj.get("name").equals("sEcho"))
//	            sEcho = obj.get("value").toString();
//	 
//	        if (obj.get("name").equals("iDisplayStart"))
//	            iDisplayStart = obj.getInt("value");
//	 
//	        if (obj.get("name").equals("iDisplayLength"))
//	            iDisplayLength = obj.getInt("value");
//	        
//	    }
//	    List<Grade_Exam_Teacher_Student> userlist = new ArrayList<Grade_Exam_Teacher_Student>();
//		List<Grade> user=gradeService.selectGradeByStudentId(student_id1);
//		System.out.println("=============");
//		for(int i=0;i<user.size();i++){
//            userlist.add(Grade_Exam_Teacher_Student.connect(user.get(i),examService.selectExamByExam_id(user.get(i).getGrade_examId()),teacherService.selectTeacherByTeacher_id(user.get(i).getGrade_teacherId()),studentService.selectStudentByStudent_id(user.get(i).getGrade_studentId())));
//        }
//		JSONObject getObj = new JSONObject();
//	    getObj.put("sEcho", sEcho);// DataTable������Ⱦ����Ϣ
//	    getObj.put("iTotalRecords", userlist.size());//ȫ�����ݵ�����
//	    getObj.put("iTotalDisplayRecords", userlist.size());//��ʾ������ 
//	    if(iDisplayStart+iDisplayLength<userlist.size())
//	    {
//	    	getObj.put("data", userlist.subList(iDisplayStart, iDisplayStart+iDisplayLength));//Ҫ��JSON��ʽ����
//	    }
//	    else
//	    {
//	    	getObj.put("data", userlist.subList(iDisplayStart, userlist.size()));//Ҫ��JSON��ʽ����
//	    }
//        return new ResponseEntity<JSONObject>(getObj,HttpStatus.OK);
//    }

	
	
	
	@RequestMapping("/allStudentsOfTeacher/{teacher_id}")  //����ʦ����ѧ����Ϣ
	public ResponseEntity<List<Student>> listStudents(@PathVariable int teacher_id)
	{
		List<Student> userlist=studentService.selectStudentByTeacherId(teacher_id);
        return new ResponseEntity<List<Student>>(userlist, HttpStatus.OK);
    }
	
	@RequestMapping("/oneStudent/{student_id}") //ĳλѧ����Ϣ
	public ResponseEntity<Student> oneStudent(@PathVariable int student_id)
	{
		Student user=studentService.selectStudentByStudent_id(student_id);
        return new ResponseEntity<Student>(user, HttpStatus.OK);
    }
	
	@RequestMapping("/oneTeacher/{teacher_id}") //ĳλѧ����Ϣ
	public ResponseEntity<Teacher> oneTeacher(@PathVariable int teacher_id)
	{
		Teacher teacher=teacherService.selectTeacherByTeacher_id(teacher_id);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }
	
	@RequestMapping("/oneTeacherByAccount") //根据账号查找老师
	public ResponseEntity<Teacher> oneTeacherByAccount(HttpServletRequest request)
	{
		String teacher_account=request.getParameter("teacher_account");
		Teacher teacher=teacherService.selectTeacherByTeacher_account(teacher_account);
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }
	

	@RequestMapping("/StudentGrade/{student_id}") //ѧ�����гɼ�
	public ResponseEntity<List<Grade>> studentGrades(@PathVariable int student_id)
	{
		List<Grade> gradelist=gradeService.selectGradeByStudentId(student_id);
        return new ResponseEntity<List<Grade>>(gradelist, HttpStatus.OK);
    }
	
	@RequestMapping("/oneGrade/{grade_id}") //ĳ���ɼ�
	public ResponseEntity<Grade> oneGrade(@PathVariable int grade_id)
	{
		Grade grade=gradeService.selectGradeByGrade_id(grade_id);
        return new ResponseEntity<Grade>(grade, HttpStatus.OK);
    }
	
	
	@RequestMapping("/updateStudent")  //ѧ����Ϣ����
	public void updateStudent(HttpServletRequest request, HttpServletResponse response) {
		int Student_id;
		
	}
	
	@RequestMapping("/updateTeacher")  //修改信息
	public void updateTeacher(HttpServletRequest request, HttpServletResponse response) {
		int teacherId=Integer.valueOf(request.getParameter("teacherId"));
		String name=request.getParameter("name");
		String tel=request.getParameter("tel");
		String school=request.getParameter("school");
		String description=request.getParameter("description");
		
		Teacher teacher=new Teacher();
		teacher.setTeacher_id(teacherId);
		teacher.setTeacher_name(name);
		teacher.setTeacher_tel(tel);
		teacher.setTeacher_account(tel);
		teacher.setTeacher_description(description);
		teacherService.updateTeacher(teacher);
	}
	@RequestMapping("/updateTeacherPassword")  //修改密码
	public void updateTeacherPassword(HttpServletRequest request, HttpServletResponse response) {
		int teacherId=Integer.valueOf(request.getParameter("teacherId"));
		String newPsw=request.getParameter("newPassword");
		Teacher teacher=new Teacher();
		teacher.setTeacher_password(newPsw);
		teacherService.updateTeacherPassword(teacher);
	}
	
	@RequestMapping("/addStudent")//老师添加学生
	public void addStudent(HttpServletRequest request, HttpServletResponse response)
	{
		int Student_schoolId=3;
		int Student_teacherId=Integer.valueOf(request.getParameter("teacher_id"));
		String Student_schname=request.getParameter("shcool_name");
		String Student_class=request.getParameter("class_name");
		String Student_name=request.getParameter("student_name");
		int Student_age=Integer.valueOf(request.getParameter("student_age"));
		String Student_sex=request.getParameter("student_sex");
		String Student_account=request.getParameter("student_account");//学号
		String Student_tel=request.getParameter("student_tel");
		String Student_password="000";
		
		Student student=new Student();
		student.setStudent_schoolId(Student_schoolId);
		student.setStudent_teacherId(Student_teacherId);
		student.setStudent_schname(Student_schname);
		student.setStudent_class(Student_class);
		student.setStudent_name(Student_name);
		student.setStudent_age(Student_age);
		student.setStudent_sex(Student_sex);
		student.setStudent_account(Student_account);
		student.setStudent_tel(Student_tel);
		student.setStudent_password(Student_password);
		studentService.saveStudentDao(student);
	}
	
	@RequestMapping("/teacherRigister")//老师注册
	public void teacherRigister(HttpServletRequest request, HttpServletResponse response)
	{
		String teacher_account=request.getParameter("teacher_account");//手机号
		String teacher_password=request.getParameter("teacher_password");//密码
		Teacher teacher=new Teacher();
		teacher.setTeacher_account(teacher_account);
		teacher.setTeacher_tel(teacher_account);
		teacher.setTeacher_password(teacher_password);
		teacherService.saveTeacherDao(teacher);
	}
	
}
