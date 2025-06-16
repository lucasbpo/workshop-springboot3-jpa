package br.com.lucasbpo.course.configuration;

import br.com.lucasbpo.course.entities.Order;
import br.com.lucasbpo.course.entities.User;
import br.com.lucasbpo.course.repositories.OrderRepository;
import br.com.lucasbpo.course.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

    private UserRepository userRepository;
    private OrderRepository orderRepository;

    public TestConfiguration(
            UserRepository userRepository,
            OrderRepository orderRepository
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var firstUser = new User(null, "Lucas", "lucas@example.com", "999000000", "secret");
        var secondUser = new User(null, "Eduardo", "eduardo@example.com", "999000000", "topsecret");

        var users = List.of(firstUser, secondUser);
        var orders = List.of(
                new Order(null, Instant.parse("2019-06-20T19:53:07Z"), firstUser),
                new Order(null, Instant.parse("2019-07-21T03:42:10Z"), secondUser),
                new Order(null, Instant.parse("2019-07-22T15:21:22Z"), firstUser)
        );

        userRepository.saveAll(users);
        orderRepository.saveAll(orders);
    }
}
