package com.epam.app;

import com.epam.entity.User;
import com.epam.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.epam"})
@EnableJpaRepositories(basePackages = {"com.epam.repository"})
@EntityScan(value = {"com.epam.entity"})
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService) {
        return new CommandLineRunner() {
            @Override

            public void run(String... strings) throws Exception {
                User user1 = new User(1, "Jack", "Malkovich", 19);
                User user2 = new User(2, "Vasya", "Stichkin", 32);
                User user3 = new User(3, "Masha", "Ivanove", 11);
                User user4 = new User(4, "Anya", "Voronina", 49);
                userService.save(user1);
                userService.save(user2);
                userService.save(user3);
                userService.save(user4);
            }
        };
    }
}
