package com.example.account_management.service;

import com.example.account_management.entity.Account;
import com.example.account_management.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Account createAccount(Account account) {
        // Hash the password before saving
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }
    public Account getAccountByName(String name) {
        return accountRepository.findByEmail(name).orElse(null);
    }

    public Account updateAccount(Long id, Account accountDetails) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setName(accountDetails.getName());
            account.setEmail(accountDetails.getEmail());
            // Ensure that password is updated only if it's not null or empty
            if (accountDetails.getPassword() != null && !accountDetails.getPassword().isEmpty()) {
                account.setPassword(passwordEncoder.encode(accountDetails.getPassword()));
            }
            return accountRepository.save(account);
        }
        return null;
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Account authenticate(String email, String password) {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (passwordEncoder.matches(password, account.getPassword())) {
                return account; // Authentication successful
            } else {
                // Handle wrong password
                throw new RuntimeException("Invalid credentials");
            }
        }
        // Handle account not found
        throw new RuntimeException("Account not found");
    }
}
