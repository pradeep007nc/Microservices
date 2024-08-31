package dev.pradeep.Microservices.respository;

import dev.pradeep.Microservices.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerDao extends CrudRepository<Customer, Long> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
