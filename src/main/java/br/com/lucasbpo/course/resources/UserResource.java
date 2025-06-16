package br.com.lucasbpo.course.resources;

import br.com.lucasbpo.course.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll() {
        var user = new User(1L, "Lucas", "lucas@example.com", "999000000", "secret");
        return ResponseEntity.ok(user);
    }

}
