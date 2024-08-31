package dev.pradeep.Microservices.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Accounts extends BaseEntity {
    @Column(name = "customer_id")
    private Long customerId;

    private String email;

    private String phoneNumber;

    private String accountType;

    private String branchAddress;

    private Long accountNumber;
}
