package com.dailycodebuffer.department.repository;

import org.springframework.stereotype.Repository;
import com.dailycodebuffer.department.entity.Department;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	@Query(value="SELECT * FROM department WHERE department_code = ?1", nativeQuery = true)
	public List<Department> findByDeptCode(Long departmentId);
	
	/*
	 * @Query("SELECT u FROM User u WHERE u.email = ?1") public User
	 * findByEmail(String email);
	 */
}
