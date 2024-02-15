package cat.copernic.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.copernic.backend.data.responses.Response;
import cat.copernic.backend.data.responses.ResponseState;
import cat.copernic.backend.services.nav_bar.NavBarService;

@CrossOrigin
@RestController
@RequestMapping("/api/nav-bar")
public class NavBarController {

    @Autowired
    private NavBarService navBarService;

    @GetMapping("/get-user-module-permissions")
    public ResponseEntity<?> getModules() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Response response = this.navBarService.getUserPermissions(auth);

        if(response.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                response.getBody()
            );
        }

        return ResponseEntity.status(400).body(
            response.getBody()
        );
    }

    @GetMapping("/get-module-permissions")
    public ResponseEntity<?> getModulePermissions(
        @RequestParam String moduleName
    ) {
        Response response = this.navBarService.getModulePermissions(moduleName);

        if(response.getStatus() == ResponseState.OK) {
            return ResponseEntity.status(200).body(
                response.getBody()
            );
        }

        return ResponseEntity.status(400).body(
            response.getBody()
        );
    }

}
