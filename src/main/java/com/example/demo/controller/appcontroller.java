package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.objects.Student;
import com.example.demo.reposatory.studentRepo;

@Controller
public class appcontroller {
	
	@Autowired
	private studentRepo repo;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/enterstudent")
	public String enterNewData() {
		return "EnterData";
	}
	
	@PostMapping("/showentery")
	public String showEnteredData(@RequestParam("id") Integer id,
			@RequestParam("name") String name,
			@RequestParam("grade") String grade, ModelMap mp) {
		mp.addAttribute("id",id);
		mp.addAttribute("name",name);
		mp.addAttribute("grade",grade);
		Student st=new Student();
		
		st.setId(id);
		st.setName(name);
		st.setGrade(grade);
		Optional<Student> st1=repo.findById(id);
		if(st1.isEmpty()) {
			repo.insert(st);
			return "showenteryy";
		}
		else {
			return "Alreadyexist";
		}
		
	}
	
	@GetMapping("/sid")
	public String studID() {
		return "StudentID";
	}
	
	@PostMapping("/showdetail")
	public String showdetails(@RequestParam("id") Integer id,
			ModelMap mp) {
		Optional<Student> st = repo.findById(id);
		if (st.isEmpty()) {
			return "blank";
		}
		else {
			mp.addAttribute("id", st.get().getId());
			mp.addAttribute("name", st.get().getName());
			mp.addAttribute("grade", st.get().getGrade());
			return "studentdata";
		}
	}
	@GetMapping("/updatedata")
	public String getdatatoupdate() {
		return "getupdatedata";
	}
	
	@PostMapping("/updatestudent")
	public String updateData(@RequestParam("id") Integer id,
			@RequestParam("name") String name,
			@RequestParam("grade") String grade, ModelMap mp) {
		mp.addAttribute("id",id);
		mp.addAttribute("name",name);
		mp.addAttribute("grade",grade);
		Optional<Student> st=repo.findById(id);
		if (st.isEmpty()) {
			Student st1= new Student();
			st1.setId(id);
			st1.setGrade(grade);
			st1.setName(name);
			repo.insert(st1);
			return "added";
		}
		else {
			Student st1= new Student();
			st1.setId(id);
			st1.setGrade(grade);
			st1.setName(name);
			repo.save(st1);
			return "Updated";
		}
}
	@GetMapping("/deletID")
	public String deletesid() {
		return "deletestid";
	}
	
	@PostMapping("/deletedata")
	public String deletestudent(@RequestParam("id") Integer id,ModelMap mp) {
		Optional<Student> st = repo.findById(id);
		if (st.isEmpty()) {
			return "blank";
		}
		else {
			mp.addAttribute("id", st.get().getId());
			mp.addAttribute("name", st.get().getName());
			mp.addAttribute("grade", st.get().getGrade());
			repo.deleteById(id);
			return "deleteScreen";
		}
	}
}
