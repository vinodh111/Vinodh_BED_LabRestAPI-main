package com.gl.security.studentmgmt.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gl.security.studentmgmt.entity.Student;
import com.gl.security.studentmgmt.service.StudentService;

@SuppressWarnings("unused")
@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String getStudents(Model theModel)
	{
		List <Student> students = studentService.findAll();
		theModel.addAttribute("Students", students);
		return "list-Students";
 	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAddingStudents(Model theModel)
	{
		Student student = new Student();
		theModel.addAttribute("Student", student);
		return "Student-form";
	}
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,Model theModel)
	{
		Student student = studentService.findById(theId);
		theModel.addAttribute("Student", student);
		return "Student-form";
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
								@RequestParam("firstName") String fName,
								@RequestParam("lastName") String lName,
								@RequestParam("course") String course,
								@RequestParam("country") String country)
	{
		Student theStudent ;
		if(id != 0)
		{
			theStudent = studentService.findById(id);
			theStudent.setFirstName(fName);
			theStudent.setLastName(lName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		}
		else
		{
			theStudent = new Student(fName,lName,course,country);
		}
		studentService.save(theStudent);
		return "redirect:/student/list";
	}
	
	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int theId)
	{
		studentService.deleteById(theId);
		return "redirect:/student/list";
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("mymsg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("mymsg", 
			"You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}