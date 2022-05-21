package db2.carrental.controllers;

import db2.carrental.entities.AuthToken;
import db2.carrental.entities.Uzytkownicy;
import db2.carrental.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/login")
    @GetMapping
    public ResponseEntity<Object> login(@RequestParam(required = true) String email, @RequestParam(required = true) String password) {
        Map<String, Object> resp = new HashMap<>();

        try {
            AuthToken token = authService.login(email, password, 0);
            resp.put("status", "success");
            resp.put("token", token);
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("status", "error");
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @RequestMapping("/admin")
    @GetMapping
    public ResponseEntity<Object> adminLogin(@RequestParam(required = true) String email, @RequestParam(required = true) String password) {
        Map<String, Object> resp = new HashMap<>();

        try {
            AuthToken token = authService.login(email, password, 1);
            resp.put("status", "success");
            resp.put("token", token);
            return ResponseEntity.ok(resp);
        }
        catch (Exception e){
            resp.put("status", "error");
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @RequestMapping("/register")
    @PutMapping
    public ResponseEntity<Object> register(@RequestBody Uzytkownicy user){
        Map<String, Object> resp = new HashMap<>();
        try {
            if (user.getUserGroupId() != 0) throw new IllegalArgumentException("Creating admin accounts is not allowed.");
            authService.registerUser(user);
            resp.put("status", "success");
            return ResponseEntity.ok(resp);

        }
        catch (Exception e){
            resp.put("status", "error");
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }

    }

    @RequestMapping("/revoke")
    @DeleteMapping
    public ResponseEntity<Object> revoke(@RequestParam(name = "token") String token){
        Map<String, Object> resp = new HashMap<>();
        try {
            authService.revoke(token);
            resp.put("status", "success");
            return ResponseEntity.ok(resp);

        }
        catch (Exception e){
            resp.put("status", "error");
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        }

    }

}
