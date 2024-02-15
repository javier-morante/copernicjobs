package cat.copernic.backend.controller.user_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.user_management.CreateStudentDTO;
import cat.copernic.backend.data.dto.user_management.UpdateStudentDTO;
import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.user_management.StudentManagementService;

@RestController
@RequestMapping("/api/user-management/student")
public class StudentManagementController {
    
    @Autowired
    private StudentManagementService studentManagementService;


    @PostMapping("/create-student")
    public ResponseEntity<?> createStudent(
        @RequestBody CreateStudentDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        Response res = this.studentManagementService.createStudent(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400 ;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @GetMapping("/get-students")
    public ResponseEntity<?> getStudents() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.studentManagementService.getUsers();
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

    @PostMapping("/update-student")
    public ResponseEntity<?> updateStudent(
        @RequestBody UpdateStudentDTO body
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.studentManagementService.updateStudent(body);
        int responseCode = (res.getStatus() == ResponseState.OK)? 200 : 400;

        return ResponseEntity.status(responseCode).body(
            res.getBody()
        );
    }

}
