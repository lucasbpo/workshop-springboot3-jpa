package br.com.lucasbpo.course.configuration;

import br.com.lucasbpo.course.entities.Category;
import br.com.lucasbpo.course.entities.Order;
import br.com.lucasbpo.course.entities.User;
import br.com.lucasbpo.course.entities.enums.OrderStatus;
import br.com.lucasbpo.course.repositories.CategoryRepository;
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
    private CategoryRepository categoryRepository;

    public TestConfiguration(
            UserRepository userRepository,
            OrderRepository orderRepository,
            CategoryRepository categoryRepository
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var firstUser = new User(null, "Lucas", "lucas@example.com", "999000000", "secret");
        var secondUser = new User(null, "Eduardo", "eduardo@example.com", "999000000", "topsecret");

        var firstCategory = new Category(null, "Electronics");
        var secondCategory = new Category(null, "Books");
        var thirdCategory = new Category(null, "Computers");

        var users = List.of(firstUser, secondUser);
        var categories = List.of(firstCategory, secondCategory, thirdCategory);
        var orders = List.of(
                new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, firstUser),
                new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, secondUser),
                new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, firstUser)
        );



        userRepository.saveAll(users);
        orderRepository.saveAll(orders);
        categoryRepository.saveAll(categories);
    }
}
