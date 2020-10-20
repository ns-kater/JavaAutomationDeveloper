package nser.restfull.webservices.homework.restfull.webservices.homework.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import nser.restfull.webservices.homework.restfull.webservices.homework.beans.Course;
import nser.restfull.webservices.homework.restfull.webservices.homework.beans.Student;
import nser.restfull.webservices.homework.restfull.webservices.homework.beans.AllServices;



@RestController
public class AllControllers {
	
	@Autowired
	private AllServices studentService;
	
	@RequestMapping(method = RequestMethod.GET, value="/students/allstudents")
	@ResponseBody
	public List<Student> retriveAllStudents(){
		return studentService.retrieveAllStudents();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/students/{studentId}")
	@ResponseBody
	public Student retriveStudent(@PathVariable String studentId){
		return studentService.retrieveStudent(studentId);
	}


	@RequestMapping(method = RequestMethod.GET, value="/students/{studentId}/courses")
	@ResponseBody
	public List<Course> retrieveCoursesForStudent(@PathVariable String studentId) {
		return studentService.retrieveCourses(studentId);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value="/students/{studentId}/courses/{courseId}")
	@ResponseBody
	public Course retrieveDetailsForCourse(@PathVariable String studentId,
			@PathVariable String courseId) {
		return studentService.retrieveCourse(studentId, courseId);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value="/students/{studentId}/courses")
	@ResponseBody
	public ResponseEntity<Void> registerStudentForCourse(
			@PathVariable String studentId, @RequestBody Course newCourse) {

		Course course = studentService.addCourse(studentId, newCourse);

		if (course == null)
			return ResponseEntity.noContent().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
				"/{id}").buildAndExpand(course.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value="/update/student")
	@ResponseBody	
	public String upDateStudent (@RequestBody Student std) {
		
		System.out.println("In updateStudent");
		return studentService.upDateStudent(std);
		
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/delete/student/{studentId}/courses")
	@ResponseBody
	public String removeStudentFromCourse(@PathVariable String studentId, @RequestBody Course course) {
		
		if (course == null)
			return null;
		
		return studentService.deleteCourse(studentId, course);
		
	}

}
