package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Teacher;
import fcu.pbiecs.springdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*") // 允許所有來源的跨域請求
@RestController // 處理HTTP請求的控制器
@RequestMapping("/api/teachers") // 定義路徑前綴
public class TeacherController {
    @Autowired // 自動注入TeacherService，這樣就可以使用教師服務
    private TeacherService teacherService;

    // 查詢所有教師資料
    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    // 查詢「指定」id教師資料
    @GetMapping("/{id}") // 完整路徑會是 /api/teachers/{id}
    public Teacher getTeacher(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    // 刪除特定教師資料
    @DeleteMapping("/{id}") // 完整路徑會是 /api/teachers
    public String deleteTeacher(@PathVariable int id) {
        boolean delete = teacherService.deleteTeacher(id);
        if(delete){
            return "Teacher with ID " + id + " deleted successfully.";
        } else {
            return "Teacher with ID " + id + " not found.";
        }
    }

    // 新增教師資料
    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    // 更新教師資料
    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(id, updatedTeacher);
    }
}