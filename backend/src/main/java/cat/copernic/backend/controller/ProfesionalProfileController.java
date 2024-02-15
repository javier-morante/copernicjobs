package cat.copernic.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.student_profesional_profile.StudentProfesionalProfileDTO;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.services.profesional_profile.ProfesionalProfileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/profesional-profile")
public class ProfesionalProfileController{
    
    @Autowired
    ProfesionalProfileService profesionalProfileService;

    Logger logger = LoggerFactory.getLogger(ProfesionalProfileController.class);

    @GetMapping
    public ResponseEntity<?> getProfesionalProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Response res = profesionalProfileService.getProfesionalProfile(auth.getName());
        return ResponseEntity.status(res.getStatus()).body(res.getBody());
    }

    @PostMapping
    public ResponseEntity<?> postProfesionalProfile(@ModelAttribute StudentProfesionalProfileDTO entity) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Response res = profesionalProfileService.updateProfesionalProfile(entity,auth.getName());
        return ResponseEntity.status(res.getStatus()).body(res.getBody());
    }
    
    
}
