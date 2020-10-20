package nser.restfull.webservices.homework.restfull.webservices.homework.beans;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


import org.springframework.stereotype.Component;


@Component
public class AllServices {

	private static List<Student> students = new ArrayList<>();

	static {
		
		
		//Initializing Data for courses as well as students obviously this also can be done through the script as well
		
		Course course1 = new Course("Crs111", "Spring", "Steps and examples", Arrays
				.asList("Maven", "Demo Project", "implemet Example",
						"solve problem"));
		Course course2 = new Course("Crs222", "Micro Serivces", "Explanation with Examples",
				Arrays.asList("Learn Maven", "Import Project", "implement Example",
						"solve problems"));
		Course course3 = new Course("Crs333", "Spring Boot", "STI Course",
				Arrays.asList("Maven project", "Learn Spring boot",
						"implement Demos", "get Example", "solve problem"));
		Course course4 = new Course("Crs444", "RestFull API",
				"Most popular API's ", Arrays.asList(
						"Spering boot project", "Maven", "implement restfull API",
						"Demo Examples"));


		Student Nser = new Student("Std111", "Nser Katerji",
				"Senior software Engineer, Programmer and Architect", new ArrayList<>(Arrays
						.asList(course1, course2, course3, course4)));

		Student Sam = new Student("Std222", "Sam Bond",
				"software Engineer , Programmer", new ArrayList<>(Arrays
						.asList(course1, course2)));

		Student Dan = new Student("Std333", "Dan Holm",
				"software Engineer, Programmer", new ArrayList<>(Arrays
						.asList(course1)));

		students.add(Nser);
		students.add(Sam);
		students.add(Dan);
	}



	public List<Student> retrieveAllStudents() {
		return students;
	}

	public Student retrieveStudent(String studentId) {
		for (Student student : students) {
			if (student.getId().equals(studentId)) {
				return student;
			}
		}
		return null;
	}

	public List<Course> retrieveCourses(String studentId) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		return student.getCourses();
	}

	public Course retrieveCourse(String studentId, String courseId) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		for (Course course : student.getCourses()) {
			if (course.getId().equals(courseId)) {
				return course;
			}
		}

		return null;
	}

	// this variable is just to generate a random unique course ID
	private SecureRandom randId = new SecureRandom();

	public Course addCourse(String studentId, Course course) {
		Student student = retrieveStudent(studentId);

		if (student == null) {
			return null;
		}

		String crsId = new BigInteger(130, randId).toString(32);
		course.setId(crsId);

		student.getCourses().add(course);

		return course;
	}

	public String deleteCourse(String studentId, Course course) {

		Student student = retrieveStudent(studentId);
		String id = course.getId();
		Boolean flag = false;
		Iterator<Course> iterator = student.getCourses().iterator();
		while (iterator.hasNext()) {
			Course crs = iterator.next();

			if (crs.getId().equals(id)) {

				iterator.remove();
				flag = true;

			}

		}

		if(flag)
			return "Delete succeeded";
		else
			return "Delete not succeeded";

	}

	public String upDateStudent(Student std) {

		for(int i=0; i<students.size(); i++)
		{
			Student stdn = students.get(i);
			System.out.println(stdn.getId());
			System.out.println(std.getId());
			if(stdn.getId().equals(std.getId())) {
				students.set(i, std);//update the new record
				return "Update successful";
			}
		}

		return "Update un-successful";

	}

}
