package com.chickendelivery.config;

import com.chickendelivery.entity.Role;
import com.chickendelivery.entity.User;
import com.chickendelivery.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminDataInitializer {

    @Bean
    CommandLineRunner createAdmin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {
            String adminEmail = "admin@chickendelivery.com";

            if (userRepository.existsByEmail(adminEmail)) {
                return;
            }

            User admin = new User();

            admin.setName("Chicken Delivery Admin");
            admin.setEmail(adminEmail);
            admin.setPhone("9999999999");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setAddress("Pune, Maharashtra");
            admin.setRole(Role.ADMIN);
            admin.setActive(true);

            userRepository.save(admin);

            System.out.println("ADMIN ACCOUNT CREATED");
            System.out.println("Email: " + adminEmail);
            System.out.println("Role: ADMIN");
        };
    }
}