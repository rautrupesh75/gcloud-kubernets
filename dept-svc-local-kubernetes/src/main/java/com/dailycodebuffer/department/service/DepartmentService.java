package com.dailycodebuffer.department.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailycodebuffer.department.entity.Department;
import com.dailycodebuffer.department.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	/*
	 * public DepartmentService(DepartmentRepository departmentRepositorys) {
	 * this.departmentRepository = departmentRepositorys; }
	 */
	
	public Department saveDepartment(Department department) {
		System.out.println("Inside Save Department method of DepartmentService");
		return departmentRepository.save(department);
	}

	public List<Department> saveAllDepartment(List<Department> departmentList) {
		System.out.println("Inside Save Department method of DepartmentService");
		return departmentRepository.saveAll(departmentList);
	}

	
	public Optional<Department> findDepartmentById(Long departmentId) {
		System.out.println("Inside findDepartmentById in DepartmentService");
		return departmentRepository.findById(departmentId);
	}
	
	public List<Department> findDepartmentByCode(Long departmentCode) {
		System.out.println("Inside findDepartmentByCode in DepartmentService");
		return (List<Department>)departmentRepository.findByDeptCode(departmentCode);
	}
}
