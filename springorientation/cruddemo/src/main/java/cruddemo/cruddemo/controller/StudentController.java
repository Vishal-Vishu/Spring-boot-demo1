package cruddemo.cruddemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cruddemo.cruddemo.entity.Student;
import cruddemo.cruddemo.repository.SpringStudentRepository;

@Controller
@RequestMapping("/students/")
public class StudentController {

    private final SpringStudentRepository springStudentRepository;

    @Autowired
    public StudentController(SpringStudentRepository springStudentRepository) {
        this.springStudentRepository = springStudentRepository;
    }

    @GetMapping("signup")
    public String showSignUpForm(Student student) {
        return "add-student";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("students", springStudentRepository.findAll());
        springStudentRepository.findAll().stream().map(s->s.getName()).forEach(System.out::println);;
        return "index";
    }

    @PostMapping("add")
    public String addStudent(@Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-student";
        }

        springStudentRepository.save(student);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student student = springStudentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        System.out.println(student.getName()+" -- "+student.getId());
        model.addAttribute("student", student);
        
        
        return "update-student";
    }

    @PostMapping("update/{id}")
    public String updateStudent(@PathVariable("id") int id, @Valid Student student, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            student.setId(id);
            return "update-student";
        }

        springStudentRepository.save(student);
        model.addAttribute("students", springStudentRepository.findAll());
        return "index";
    }

    @GetMapping("delete/{id}")
    public String deleteStudent(@PathVariable("id") int id, Model model) {
        Student student = springStudentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        
        springStudentRepository.delete(student);
        model.addAttribute("students", springStudentRepository.findAll());
        return "index";
    }
}