package com.task.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.task.Bean.Exam;
import com.task.Bean.Grade;
import com.task.Bean.Grade_Exam_Teacher_Student;
import com.task.Bean.Student;
import com.task.Service.ExamService;
import com.task.Service.GradeService;
import com.task.Service.StudentService;
import com.task.Service.TeacherService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/Exam")
public class ExamMsgController {

	@Autowired
    private StudentService studentService;
	
	@Autowired
    private TeacherService teacherService;
	@Autowired
    private GradeService gradeService;
	
	@Autowired
    private ExamService examService;
	
	
	@RequestMapping("/ExamStudentGrade") //ѧ�����гɼ�
	public ResponseEntity<JSONObject> StudentGrade(@RequestParam String  exam_id,@RequestParam String aoData,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		String sEcho = null;
		int exam_id1 =Integer.parseInt(exam_id);
		System.out.println(exam_id1);
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
	    List<Grade_Exam_Teacher_Student> userlist = new ArrayList<Grade_Exam_Teacher_Student>();
		List<Grade> user=gradeService.selectGradeByExamId(exam_id1);
		System.out.println("=============");
		for(int i=0;i<user.size();i++){
            userlist.add(Grade_Exam_Teacher_Student.connect(user.get(i),examService.selectExamByExam_id(user.get(i).getGrade_examId()),teacherService.selectTeacherByTeacher_id(user.get(i).getGrade_teacherId()),studentService.selectStudentByStudent_id(user.get(i).getGrade_studentId())));
        }
		JSONObject getObj = new JSONObject();
	    getObj.put("sEcho", sEcho);// DataTable������Ⱦ����Ϣ
	    getObj.put("iTotalRecords", userlist.size());//ȫ�����ݵ�����
	    getObj.put("iTotalDisplayRecords", userlist.size());//��ʾ������ 
	    if(iDisplayStart+iDisplayLength<userlist.size())
	    {
	    	getObj.put("data", userlist.subList(iDisplayStart, iDisplayStart+iDisplayLength));//Ҫ��JSON��ʽ����
	    }
	    else
	    {
	    	getObj.put("data", userlist.subList(iDisplayStart, userlist.size()));//Ҫ��JSON��ʽ����
	    }
        return new ResponseEntity<JSONObject>(getObj,HttpStatus.OK);
    }
	
	
	@RequestMapping("/allExam") //��ʾ�����Ծ�
	public ResponseEntity<JSONObject> allExam(@RequestParam String aoData,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		JSONArray jsonarray = JSONArray.fromObject(aoData);
		String sEcho = null;
	    int iDisplayStart = 0; // ��ʼ����
	    int iDisplayLength = 0; // ÿҳ��ʾ������
	 System.out.println("00000000");
	    for (int i = 0; i < jsonarray.size(); i++) {
	        JSONObject obj = (JSONObject) jsonarray.get(i);
	        if (obj.get("name").equals("sEcho"))
	            sEcho = obj.get("value").toString();
	 
	        if (obj.get("name").equals("iDisplayStart"))
	            iDisplayStart = obj.getInt("value");
	 
	        if (obj.get("name").equals("iDisplayLength"))
	            iDisplayLength = obj.getInt("value");
	        
	    }
	    List<Exam> userlist = examService.selectExam();
		JSONObject getObj = new JSONObject();
	    getObj.put("sEcho", sEcho);// DataTable������Ⱦ����Ϣ
	    getObj.put("iTotalRecords", userlist.size());//ȫ�����ݵ�����
	    getObj.put("iTotalDisplayRecords", userlist.size());//��ʾ������ 
	    if(iDisplayStart+iDisplayLength<userlist.size())
	    {
	    	getObj.put("data", userlist.subList(iDisplayStart, iDisplayStart+iDisplayLength));//Ҫ��JSON��ʽ����
	    }
	    else
	    {
	    	getObj.put("data", userlist.subList(iDisplayStart, userlist.size()));//Ҫ��JSON��ʽ����
	    }
        return new ResponseEntity<JSONObject>(getObj,HttpStatus.OK);
    }
	
	
	@RequestMapping("/allExamsOfTeacher/{teacher_id}")  //��ѯ����ʦ���гɼ���Ϣ
	public ResponseEntity<List<Exam>> listExams(@PathVariable int teacher_id)
	{
		List<Exam> examlist=examService.selectExamByTeacherId(teacher_id);
        return new ResponseEntity<List<Exam>>(examlist, HttpStatus.OK);
    }
	
	
	@RequestMapping("/oneExamOfTeacher/{exam_id}")  //��ѯ�����Ծ���Ϣ
	public ResponseEntity<Exam> oneExam(@PathVariable int exam_id)
	{ 
		Exam exam=examService.selectExamByExam_id(exam_id);
        return new ResponseEntity<Exam>(exam, HttpStatus.OK);
    }
	
	@RequestMapping("/Grade/{exam_id}") //���Ծ�ɼ�
	public ResponseEntity<List<Grade>> studentGrades(@PathVariable int exam_id)
	{
		List<Grade> gradelist=gradeService.selectGradeByExamId(exam_id);
        return new ResponseEntity<List<Grade>>(gradelist, HttpStatus.OK);
    }
	
	@RequestMapping("/saveExam")  //�����Ծ�
	public void saveExam(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
	{
		String exam_name;
		int teacher_id;

		exam_name=request.getParameter("exam_name");
		teacher_id=Integer.parseInt(request.getParameter("teacher_id"));
		
		Date date = new Date();//���ϵͳʱ��.
		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        System.out.println(exam_name+"===="+teacher_id+"==="+nowTime);
		Exam newExam=new Exam();
		newExam.setExam_name(exam_name);
		newExam.setExam_teacherId(teacher_id);
		newExam.setExam_time(time);
		examService.saveExamDao(newExam);
	}
	
	@RequestMapping("/saveGrade")  //录入成绩
	public void saveGrade(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
	{
		int grade_studentId;
		int grade_teacherId;
		int grade_examId;
		String grade_wrong;
		int grade_num;
		
		grade_studentId=Integer.parseInt(request.getParameter("student_id"));
		grade_teacherId=Integer.parseInt(request.getParameter("teacher_id"));
		grade_examId=Integer.parseInt(request.getParameter("exam_id"));
		grade_num=Integer.parseInt(request.getParameter("grade_num"));
		grade_wrong=request.getParameter("grade_wrong");
		System.out.println("======"+grade_studentId+"====="+grade_examId);
		
		Grade newGrade=new Grade();
		newGrade.setGrade_examId(grade_examId);
		newGrade.setGrade_studentId(grade_studentId);
		newGrade.setGrade_teacherId(grade_teacherId);
		newGrade.setGrade_wrong(grade_wrong);
		newGrade.setGrade_num(grade_num);
		gradeService.saveGradeDao(newGrade);
	}
	
}
