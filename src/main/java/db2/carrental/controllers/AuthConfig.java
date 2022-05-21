package db2.carrental.controllers;

import db2.carrental.entities.Uzytkownicy;
import db2.carrental.services.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    //TODO: Take care of this (testing purposes only)
    @Bean
    public CommandLineRunner createAdminAccount(AuthService authService) {
        return args -> {
            try {
                authService.registerUser(new Uzytkownicy("admin@example.com", 1, "admin"));
            }
            catch (IllegalArgumentException ignored) {
            }
            };
        }
}
