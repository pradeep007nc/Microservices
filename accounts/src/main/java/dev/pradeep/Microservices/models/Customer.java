package dev.pradeep.Microservices.models;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends BaseEntity {
    private String name;
    private String email;
    private String mobileNumber;
}
