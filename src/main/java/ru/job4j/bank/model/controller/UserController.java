package ru.job4j.bank.model.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.job4j.bank.model.User;
import ru.job4j.bank.service.BankService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Oywayten 26.05.2023.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class.getSimpleName());

    private final BankService bankService;

    private final ObjectMapper objectMapper;

    public UserController(BankService bankService, ObjectMapper objectMapper) {
        this.bankService = bankService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public User save(@RequestBody Map<String, String> body) {
        var username = body.get("username");
        var password = body.get("password");
        if (password.length() < 6) {
            throw new IllegalArgumentException("Invalid password. Password length must be more than 5 characters.");
        }
        var user = new User(username, password);
        bankService.addUser(user);
        return user;
    }

    @GetMapping
    public User findByPassport(@RequestParam String password) {
        return bankService.findByPassport(password).orElse(null);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() {
            {
                put("message", e.getMessage());
                put("type", e.getClass());
            }
        }));
        LOGGER.error(e.getLocalizedMessage());
    }

}