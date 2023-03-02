package com.dailycodebuffer.department.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import com.dailycodebuffer.department.entity.Department;
import com.dailycodebuffer.department.service.DepartmentService;
import org.springframework.web.multipart.MultipartFile;

// http://localhost:8080/departments/ -- Post

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;	 
	 
	@PostMapping("/saveDept")
	public Department saveDepartment(@RequestBody Department department) {
		System.out.println("Inside Save Department method of DepartmentController");
		System.out.println("Inside Save Department method of departmentName" + department.getDepartmentName());
		System.out.println("Inside Save Department method of Address" + department.getDepartmentAddress());
		return departmentService.saveDepartment(department);
	}

	@PostMapping("/saveAllDept")
	public List<Department> saveAllDepartment(@RequestBody List<Department> departmentList) {
		System.out.println("Inside Save All Department method of DepartmentController");
		System.out.println("Inside Save All Department method of departmentName" + departmentList.get(0).getDepartmentName());
		System.out.println("Inside Save Department method of Address" + departmentList.get(0).getDepartmentAddress());
		return departmentService.saveAllDepartment(departmentList);
	}

	@GetMapping("/{id}")
	public Optional<Department> findDepartmentById(@PathVariable("id") Long departmentId) {
		System.out.println("Inside findDepartmentById");
		return departmentService.findDepartmentById(departmentId);
	}

	@GetMapping("/code/{code}")
	public List<Department> findDepartmentByCode(@PathVariable("code") Long departmentCode) {
		System.out.println("Inside findDepartmentById");
		return (List<Department>) departmentService.findDepartmentByCode(departmentCode);
	}

	private String getDateTime() {
		LocalDate date = LocalDate.now();
		LocalDateTime current = LocalDateTime.now();
		System.out.println("current date and time : " + current);
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmmss");
		String formatedDateTime = current.format(format);
		System.out.println("formatedDateTime : " + formatedDateTime);
		return (formatedDateTime);
	}


}
