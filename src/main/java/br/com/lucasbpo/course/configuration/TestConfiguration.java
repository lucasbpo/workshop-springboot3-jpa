package br.com.lucasbpo.course.configuration;

import br.com.lucasbpo.course.entities.User;
import br.com.lucasbpo.course.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

    private UserRepository userRepository;

    public TestConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var users = List.of(
                new User(null, "Lucas", "lucas@example.com", "999000000", "secret"),
                new User(null, "Eduardo", "eduardo@example.com", "999000000", "topsecret")
        );

        userRepository.saveAll(users);
    }
}
