package br.com.lucasbpo.course.configuration;

import br.com.lucasbpo.course.entities.Category;
import br.com.lucasbpo.course.entities.Order;
import br.com.lucasbpo.course.entities.OrderItem;
import br.com.lucasbpo.course.entities.Payment;
import br.com.lucasbpo.course.entities.Product;
import br.com.lucasbpo.course.entities.User;
import br.com.lucasbpo.course.entities.enums.OrderStatus;
import br.com.lucasbpo.course.repositories.CategoryRepository;
import br.com.lucasbpo.course.repositories.OrderItemRepository;
import br.com.lucasbpo.course.repositories.OrderRepository;
import br.com.lucasbpo.course.repositories.ProductRepository;
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
    private OrderItemRepository orderItemRepository;
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public TestConfiguration(
            UserRepository userRepository,
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        var firstUser = new User(null, "Lucas", "lucas@example.com", "999000000", "secret");
        var secondUser = new User(null, "Eduardo", "eduardo@example.com", "999000000", "topsecret");

        var firstCategory = new Category(null, "Electronics");
        var secondCategory = new Category(null, "Books");
        var thirdCategory = new Category(null, "Computers");

        var firstProduct = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        var secondProduct = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        var thirdProduct = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        var fourthProduct = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        var fifthProduct = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        var users = List.of(firstUser, secondUser);
        var categories = List.of(firstCategory, secondCategory, thirdCategory);
        var products = List.of(firstProduct, secondProduct, thirdProduct, fourthProduct, fifthProduct);
        var orders = List.of(
                new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, firstUser),
                new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, secondUser),
                new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, firstUser)
        );

        userRepository.saveAll(users);
        orderRepository.saveAll(orders);
        categoryRepository.saveAll(categories);
        productRepository.saveAll(products);

        firstProduct.getCategories().add(secondCategory);
        secondProduct.getCategories().addAll(List.of(firstCategory, thirdCategory));
        thirdProduct.getCategories().add(thirdCategory);
        fourthProduct.getCategories().add(thirdCategory);
        fifthProduct.getCategories().add(secondCategory);

        productRepository.saveAll(products);

        var orderItems = List.of(
                new OrderItem(orders.getFirst(), products.getFirst(), 2, products.getFirst().getPrice()),
                new OrderItem(orders.getFirst(), products.get(2), 1, products.get(2).getPrice()),
                new OrderItem(orders.get(1), products.get(2), 2, products.get(2).getPrice()),
                new OrderItem(orders.get(2), products.get(4), 2, products.get(4).getPrice())
        );

        orderItemRepository.saveAll(orderItems);

        var orderPaid = orders.getFirst();
        var pay = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), orderPaid);
        orderPaid.setPayment(pay);

        orderRepository.save(orderPaid);
    }
}
