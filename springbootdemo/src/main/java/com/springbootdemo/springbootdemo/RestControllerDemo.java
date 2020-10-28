package com.springbootdemo.springbootdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class RestControllerDemo {
	
	@Autowired
	SpringStudentRepository springStudentRepository;

	@RequestMapping("/")
	public String sendMessage(){
		
		return "This is a Spring Boot Session";
	}
	
	/*@RequestMapping("/name")
	public String returnGreeting(@RequestParam(name="nameval")String name){
		return "My Name is "+name;
	}*/
	
	@RequestMapping("/name")
	public String returnGreeting(@RequestParam(name="nameval")String name,@RequestParam(name="course")String course){
		Student std = new Student();
		std.setStudentname(name);
		std.setCourse(course);
		springStudentRepository.save(std);
		return "My Name is "+name;
	}
}
