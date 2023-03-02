package com.dailycodebuffer.department.controller;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.*;
import com.dailycodebuffer.department.entity.Department;
import com.dailycodebuffer.department.service.DepartmentService;
import org.springframework.web.multipart.MultipartFile;

// http://localhost:9001/departmentsUI/ -- Post

@Controller
@RequestMapping("/departmentsUI")
public class DepartmentUIController {

	@Autowired
	private DepartmentService departmentService;

	 @GetMapping("/")
	    public String main(Model model) {
	        model.addAttribute("message", "Welcome !!!S");
	       //model.addAttribute("department", department);

	        return "upload"; //view
	    }	 
	
		public Department saveDepartment(@RequestBody Department department) {
			System.out.println("Inside Save Department method of DepartmentController");
			System.out.println("Inside Save Department method of departmentName" + department.getDepartmentName());
			System.out.println("Inside Save Department method of Address" + department.getDepartmentAddress());
			return departmentService.saveDepartment(department);
		}
		
		@PostMapping("/upload")
		public String handleFormSubmit(@RequestParam("image") MultipartFile multipartFile1,
				@RequestParam("documentFile") MultipartFile multipartFile3, Model model) throws IOException {

			System.out.println("inside handleFormSubmit ");
			String profilePictureFileName = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
			String documentFileName = StringUtils.cleanPath(multipartFile3.getOriginalFilename());

			Department department = new Department();
			department.setDepartmentCode("007");
			department.setDepartmentName("fileUpload");
			department.setDepartmentAddress("DateTime::" + getDateTime());
			department.setProfilePicture(profilePictureFileName);
			department.setDocument(documentFileName);

			Department savedDept = departmentService.saveDepartment(department);
			String uploadDir = "department/" + savedDept.getDepartmentId();

			saveFile(uploadDir, profilePictureFileName, multipartFile1);
			saveFile(uploadDir, documentFileName, multipartFile3);

			model.addAttribute("message", "Successfully Uploaded !!!");
			return "upload";
		}
		
		@GetMapping("/code/{code}")
		//public List<Department> findDepartmentByCode(@PathVariable("code") Long departmentCode) {
		public String findDepartmentByCode(@PathVariable("code") Long departmentCode,  Model model) {
			System.out.println("Inside findDepartmentById");
			List<Department> deptList = (List<Department>) departmentService.findDepartmentByCode(departmentCode);
			System.out.println("Dept List size >>"+ deptList.size());
			model.addAttribute("message", new Integer(deptList.size()).toString());
			//return (List<Department>) departmentService.findDepartmentByCode(departmentCode);
			return "upload";
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

		public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
			System.out.println("uploadDir>>"+uploadDir);
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);		
				}

			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException ioe) {
				throw new IOException("Could not save image file: " + fileName, ioe);
			}
		}
}
