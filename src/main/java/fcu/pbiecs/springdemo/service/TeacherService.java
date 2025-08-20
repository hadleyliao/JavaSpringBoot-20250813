package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    // 自動注入資料庫服務
    // 這樣就可以使用資料庫服務來處理教師相關的業務
    // 邏輯，例如查詢教師資料、更新教師資料等。
    // 注意：這裡沒有使用@Service註解，因為這個類別可能是用於測試或其他目的，
    // 但如果需要將其作為Spring管理的Bean，可以加上@Service註解。
    @Autowired
    private DatabaseService dbService;

    // 查詢「所有」教師
    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();

        String sql = "SELECT * FROM Teacher";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                // 使用建構子來創建Teacher物件
                int teacherId = rs.getInt("teacher_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                Teacher teacher = new Teacher(teacherId, name, email, age);

                teachers.add(teacher);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teachers;
    }


    // 查詢「指定」id教師資料
    public Teacher getTeacherById(int teacherId) {
        String sql = "SELECT * FROM Teacher WHERE teacher_id=?";
        try (Connection conn = dbService.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, teacherId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // 使用建構子來創建Teacher物件
                    int id = rs.getInt("teacher_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    int age = rs.getInt("age");
                    return new Teacher(teacherId, name, email, age);
                }
            } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 如果沒有找到教師，返回null
    }

    // 刪除教師資料
    public boolean deleteTeacher(int teacherId) {
        String sql = "DELETE FROM Teacher WHERE teacher_id=?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, teacherId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 如果有刪除成功的行數，返回true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // 如果沒有刪除成功，返回false
    }

    // 新增教師資料
    // 資料庫的TeacherId是勾選自動遞增（AutoIncrement）的，所以在此新增時，不需要手動設定Id，即不用給欄位。
    // AutoIncrement 的意思是資料庫會自動為每一筆新增的資料分配一個唯一的Id，程式碼這邊不需要額外處理。
    // 相反，如果資料庫的Id不是勾選自動遞增（AutoIncrement）的，那麼在新增資料時，就需要手動設定Id，即手動給欄位。
    public Teacher createTeacher(Teacher teacher) {
        String sql = "INSERT INTO Teacher (name, email, age) VALUES (?, ?, ?)";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getEmail());
            pstmt.setInt(3, teacher.getAge());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return new Teacher(generatedId, teacher.getName(), teacher.getEmail(), teacher.getAge());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 如果新增失敗，返回null
    }


    // 更新教師資料
    public Teacher updateTeacher(int id, Teacher teacher) {
        String sql = "UPDATE Teacher SET name=?, email=?, age=? WHERE teacher_id=?";
        try (Connection conn = dbService.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, teacher.getName());
            pstmt.setString(2, teacher.getEmail());
            pstmt.setInt(3, teacher.getAge());
            pstmt.setInt(4, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return new Teacher(id, teacher.getName(), teacher.getEmail(), teacher.getAge());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 如果更新失敗，返回null
    }
}
