package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.services.student_profile_modal.StudentProfileModalService;


@RestController
@RequestMapping("/api/student")
public class StudentProfileModalController {
    @Autowired
    private StudentProfileModalService studentProfileModalService;

    @GetMapping("/get-by-email")
    public ResponseEntity<?> getStudentByEmail(@RequestParam String email) {
        Response res = studentProfileModalService.getStudentData(email);
        
        return ResponseEntity.status(200).body(res.getBody());
    }
}
