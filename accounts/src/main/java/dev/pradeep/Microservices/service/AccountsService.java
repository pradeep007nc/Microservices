package dev.pradeep.Microservices.service;

import dev.pradeep.Microservices.constants.AccountsConstants;
import dev.pradeep.Microservices.dtos.AccountsDto;
import dev.pradeep.Microservices.dtos.CustomerDto;
import dev.pradeep.Microservices.exception.CustomerExistException;
import dev.pradeep.Microservices.exception.ResourceNotFoundException;
import dev.pradeep.Microservices.mapper.AccountsMapper;
import dev.pradeep.Microservices.mapper.CustomerMapper;
import dev.pradeep.Microservices.models.Accounts;
import dev.pradeep.Microservices.models.Customer;
import dev.pradeep.Microservices.respository.AccountsDao;
import dev.pradeep.Microservices.respository.CustomerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountsService {

    private final CustomerDao customerDao;
    private final AccountsDao accountsDao;

    public void createAccount(CustomerDto customerDto) {
        if (customerDao.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
            throw new CustomerExistException("Customer already exists");
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerDao.save(customer);
        accountsDao.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerDao.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Accounts accounts = accountsDao.findByCustomerId(customer.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsDao.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsDao.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerDao.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerDao.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }


    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerDao.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsDao.deleteByCustomerId(customer.getId());
        customerDao.deleteById(customer.getId());
        return true;
    }
}
