package fcu.pbiecs.springdemo.controller;

import fcu.pbiecs.springdemo.model.Teacher;
import fcu.pbiecs.springdemo.service.TeacherService;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "教師管理", description = "提供教師 CRUD API")
@CrossOrigin("*") // 允許所有來源的跨域請求
@RestController // 處理HTTP請求的控制器
@RequestMapping("/api/teachers") // 定義路徑前綴
public class TeacherController {

    @Autowired // 自動注入TeacherService，這樣就可以使用教師服務
    private TeacherService teacherService;

    @Operation(summary = "查詢所有教師", description = "取得所有教師的資訊")
    // 查詢所有教師資料
    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @Operation(summary ="查詢教師", description = "依照ID查詢教師資訊")
    // 查詢「指定」id教師資料
    @GetMapping("/{id}") // 完整路徑會是 /api/teachers/{id}
    public Teacher getTeacher(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @Operation(summary = "刪除教師", description = "依照ID刪除教師")
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

    @Operation(summary = "刪除教師", description = "依照ID刪除教師")
    // 新增教師資料
    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    @Operation(summary="更新教師", description = "依照ID更新教師資訊")
    // 更新教師資料
    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(id, updatedTeacher);
    }
}