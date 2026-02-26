package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService studentService;

    // 1. Trang Danh Sách - GET /students
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if (keyword != null && !keyword.isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        model.addAttribute("keyword", keyword);
        return "students";
    }

    // 2. Trang Chi Tiết - GET /students/{id}
    @GetMapping("/{id}")
    public String getStudentDetail(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-detail";
    }

    // 3. Trang Thêm Mới - GET /students/new
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "student-form";
    }

    // 4. Xử lý Thêm Mới - POST /students
    @PostMapping
    public String createStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    // 5. Trang Chỉnh Sửa - GET /students/{id}/edit
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentService.getById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "student-form";
    }

    // 6. Xử lý Chỉnh Sửa - POST /students/{id}
    @PostMapping("/{id}")
    public String updateStudent(@PathVariable String id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.save(student);
        return "redirect:/students";
    }

    // 7. Xóa Sinh Viên - POST /students/{id}/delete
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }
}
