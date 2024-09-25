// package com.example.account_management.service;

// import com.example.account_management.entity.Account;
// import com.example.account_management.repository.AccountRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// class AccountServiceTest {

//     @InjectMocks
//     private AccountService accountService;

//     @Mock
//     private AccountRepository accountRepository;

//     @BeforeEach
//     void setUp() {
//         MockitoAnnotations.openMocks(this);
//     }

//     @Test
//     void testCreateAccount() {
//         Account account = new Account();
//         account.setName("John Doe");
//         account.setEmail("john.doe@example.com");
//         account.setPassword("password123");

//         when(accountRepository.save(any(Account.class))).thenReturn(account);

//         Account createdAccount = accountService.createAccount(account);

//         assertNotNull(createdAccount);
//         assertEquals("John Doe", createdAccount.getName());
//         verify(accountRepository, times(1)).save(any(Account.class));
//     }

//     @Test
//     void testGetAllAccounts() {
//         List<Account> accounts = new ArrayList<>();
//         accounts.add(new Account());
//         when(accountRepository.findAll()).thenReturn(accounts);

//         List<Account> result = accountService.getAllAccounts();

//         assertNotNull(result);
//         assertEquals(1, result.size());
//     }

//     @Test
//     void testGetAccountById() {
//         Account account = new Account();
//         account.setId(1L);
//         when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

//         Account result = accountService.getAccountById(1L);

//         assertNotNull(result);
//         assertEquals(1L, result.getId());
//     }

//     @Test
//     void testUpdateAccount() {
//         Account existingAccount = new Account();
//         existingAccount.setId(1L);
//         existingAccount.setName("John Doe");
//         existingAccount.setEmail("john.doe@example.com");
//         existingAccount.setPassword("password123");

//         Account updatedDetails = new Account();
//         updatedDetails.setName("Jane Doe");
//         updatedDetails.setEmail("jane.doe@example.com");
//         updatedDetails.setPassword("newpassword123");

//         when(accountRepository.findById(1L)).thenReturn(Optional.of(existingAccount));
//         when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

//         Account result = accountService.updateAccount(1L, updatedDetails);

//         assertNotNull(result);
//         assertEquals("Jane Doe", result.getName());
//         assertEquals("jane.doe@example.com", result.getEmail());
//         verify(accountRepository, times(1)).save(any(Account.class));
//     }

//     @Test
//     void testDeleteAccount() {
//         accountService.deleteAccount(1L);
//         verify(accountRepository, times(1)).deleteById(1L);
//     }
// }
