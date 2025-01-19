package com.example.account_management.controller;

import com.example.account_management.entity.Account;
import com.example.account_management.service.AccountService;
import com.example.account_management.utills.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return account != null ? ResponseEntity.ok(account) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @Valid @RequestBody Account accountDetails) {
        Account updatedAccount = accountService.updateAccount(id, accountDetails);
        return updatedAccount != null ? ResponseEntity.ok(updatedAccount) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Account loginRequest) {
        try {
            Account authenticatedAccount = accountService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            String token = jwtTokenUtil.generateToken(authenticatedAccount.getEmail());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("accountId", authenticatedAccount.getId().toString());
            response.put("email", authenticatedAccount.getEmail());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/upload-bank-statement")
    public ResponseEntity<?> processBankStatement(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded.");
        }

        try {
            // Parse CSV file
            List<Map<String, String>> transactions = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines()
                    .skip(1) // Skip header row
                    .map(line -> {
                        String[] fields = line.split(",");
                        Map<String, String> transaction = new HashMap<>();
                        transaction.put("date", fields[0]);
                        transaction.put("description", fields[1]);
                        transaction.put("amount", fields[2]);
                        return transaction;
                    })
                    .collect(Collectors.toList());

            // Mock ChatGPT categorization (replace with actual API integration)
            List<Map<String, String>> categorizedData = transactions.stream()
                    .map(transaction -> {
                        transaction.put("category", categorizeTransaction(transaction.get("description")));
                        return transaction;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(categorizedData);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file: " + e.getMessage());
        }
    }

    private String categorizeTransaction(String description) {
        // Replace with ChatGPT API call logic
        if (description.toLowerCase().contains("amazon")) {
            return "Shopping";
        } else if (description.toLowerCase().contains("grocery")) {
            return "Groceries";
        } else if (description.toLowerCase().contains("salary")) {
            return "Income";
        } else if (description.toLowerCase().contains("electricity")) {
            return "Utilities";
        } else {
            return "Other";
        }
    }
}
