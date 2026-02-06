package vn.edu.hcmut.cse.adsoftweng.lab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;
import vn.edu.hcmut.cse.adsoftweng.lab.entity.Student;
import vn.edu.hcmut.cse.adsoftweng.lab.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentWebController {
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public String getAllStudents(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students;
        if(keyword != null && !keyword.isEmpty()) {
            students = studentService.searchByName(keyword);
        } else {
            students = studentService.getAll();
        }
        model.addAttribute("dsSinhVien", students);
        return "students";
    }
}
