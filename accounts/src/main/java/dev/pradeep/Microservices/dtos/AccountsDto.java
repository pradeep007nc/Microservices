package dev.pradeep.Microservices.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {
    @Schema(
            description = "Account Number of Eazy Bank account", example = "3454433243"
    )
    @Pattern(regexp = "^[0-9]{10}$", message = "Account Number must be 10 digits")

    private Long accountNumber;

    @NotBlank
    private String accountType;

    @NotBlank(message = "Please provide branch address")
    private String branchAddress;
}
