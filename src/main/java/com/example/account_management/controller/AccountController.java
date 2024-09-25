package com.example.account_management.controller;

import com.example.account_management.entity.Account;
import com.example.account_management.service.AccountService;
import com.example.account_management.utills.JwtTokenUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/accounts")
// Uncomment and update CrossOrigin if needed for your frontend
// @CrossOrigin(origins = "http://localhost:3000")
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

    // @PostMapping("/login")
    // public ResponseEntity<Account> login(@RequestBody Account loginRequest) {
    //     try {
    //         Account authenticatedAccount = accountService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
    //         return ResponseEntity.ok(authenticatedAccount);
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    //     }
    // }

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
}
