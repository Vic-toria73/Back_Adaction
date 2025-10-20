package com.adaction.Adaction.controller;

import com.adaction.Adaction.DAO.UserDao;
import com.adaction.Adaction.dto.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class LoginController {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        // Vérifie dans la table Admin
        Map<String, Object> admin = userDao.findAdminByFirstname(request.getfirstname());
        if (admin != null) {
            String password = (String) admin.get("password");
            if (passwordEncoder.matches(request.getPassword(), password)) {
                session.setAttribute("role", "ADMIN");
                session.setAttribute("firstname", admin.get("firstname"));
                return ResponseEntity.ok(Map.of("message", "Connexion réussie", "role", "ADMIN"));
            }
        }

        // Sinon vérifie dans la table volunteers
        Map<String, Object> vol = userDao.findVolunteerByFirstname(request.getfirstname());
        if (vol != null) {
            String password = (String) vol.get("password");
            if (passwordEncoder.matches(request.getPassword(), password)) {
                session.setAttribute("role", "VOLONTAIRE");
                session.setAttribute("firstname", vol.get("firstname"));
                return ResponseEntity.ok(Map.of("message", "Connexion réussie", "role", "VOLONTAIRE"));
            }
        }

        // Aucun utilisateur trouvé
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Identifiants invalides"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> currentUser(HttpSession session) {
        String role = (String) session.getAttribute("role");
        String firstname = (String) session.getAttribute("firstname");

        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Non connecté"));
        }
        return ResponseEntity.ok(Map.of("firstname", firstname, "role", role));
    }
}