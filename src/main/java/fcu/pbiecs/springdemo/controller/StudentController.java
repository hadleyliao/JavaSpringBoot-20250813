package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Student;
import fcu.pbiecs.springdemo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "學生管理", description = "提供學生 CRUD API")
@CrossOrigin("*") // 允許所有來源的跨域請求
@RestController // 處理HTTP請求的控制器
@RequestMapping("/api/students") // 定義路徑前綴
public class StudentController {

    @Autowired // 自動注入StudentService，這樣就可以使用學生服務
    private StudentService studentService;

    @Operation(summary = "查詢所有學生", description = "取得所有學生的資訊")
    // 查詢所有學生資料
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @Operation(summary = "查詢學生", description = "依照ID查詢學生資訊")
    // 查詢「指定」id學生資料
    @GetMapping("/{id}") // 完整路徑會是 /api/students
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @Operation(summary = "刪除學生", description = "依照ID刪除學生")
    // 刪除特定學生資料
    @DeleteMapping("/{id}") // 完整路徑會是 /api/students
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudentById(id);
    }

    @Operation(summary = "新增學生", description = "新增一位學生")
    // 新增學生資料
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @Operation(summary = "更新學生", description = "依照ID更新學生資訊")
    // 更新學生資料
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }
    /*
    private List<Student> students = new ArrayList<>();

    public StudentController() {
        // 初始化一些學生資料
        students.add(new Student(1, "Tom", "Doel", "tom@yahoo.com.tw", "2000-01-01"));
        students.add(new Student(2, "Mary", "Wang", "maryw@yahoo.com.tw", "1999-10-01"));
    }

    // 查詢所有學生資料
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }


    // 查詢特定指定id學生資料
    @GetMapping("/{id}") // 完整路徑會是 /api/students/{id}
    public Student getStudent(@PathVariable int id) {
        for (Student student : students) {
            if (student.getStudentId() == id) {
                return student;
            }
        }
        return null;
    }

    // 刪除特定學生資料
    @DeleteMapping("/{id}") // 完整路徑會是 /api/students/{id}
    public String deleteStudent(@PathVariable int id) {
        for (Student student : students) {
            if (student.getStudentId() == id) {
                students.remove(student);
                return "Student with ID " + id + " deleted successfully.";
            }
        }
        return "Student with ID " + id + " not found.";
    }


    // 新增學生資料
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        student.setStudentId(students.get(students.size() - 1).getStudentId() + 1); // 設定新的學生ID為目前最後一個學生ID加1，有可能第id=1資料被刪除，只剩id=2的資料
        //student.setStudentId(students.size() + 1); // 設定新的學
        students.add(student); // 因為這個還沒有串資料庫，所以沒有id autoincrement的功能，可以直接在postman測試時，手動設定id
        return student;
    }

    // 更新學生資料
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId() == id) {
                updatedStudent.setStudentId(id);
                students.set(i, updatedStudent);
                return updatedStudent;
            }
        }
        return null; // 如果找不到學生，返回null
    }
    */
}