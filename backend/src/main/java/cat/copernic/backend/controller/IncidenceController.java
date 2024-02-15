package cat.copernic.backend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.dto.incidence.IncidenceDTO;
import cat.copernic.backend.data.dto.incidence.IncidenceNoIdDTO;
import cat.copernic.backend.data.models.Response;
import cat.copernic.backend.services.incidence.IncidenceSService;

@RestController
@RequestMapping("/api/incidence")
public class IncidenceController {
    @Autowired
    IncidenceSService incidenceService;

    Logger logger = LoggerFactory.getLogger(IncidenceController.class);

    @PostMapping
    public ResponseEntity<?> createIncidence(@RequestBody IncidenceNoIdDTO IncidenceDTO) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Response res = incidenceService.save(IncidenceDTO, auth.getName());

            return ResponseEntity.status(res.getStatus()).body(res.getBody());
    }

    @GetMapping
    public ResponseEntity<?> getIncidences() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Response res = this.incidenceService.getAll(auth.getName());

        return ResponseEntity.status(res.getStatus()).body(res.getBody());
    }

    @PostMapping("/update")
    public ResponseEntity<?> postMethodName(@RequestBody IncidenceDTO IncidenceDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response res = this.incidenceService.update(IncidenceDTO, auth.getName());
        return ResponseEntity.status(res.getStatus()).body(res.getBody());
    }

}
