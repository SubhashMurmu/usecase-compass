package io.mosip.compass.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private UUID userInfoId;

    @NotBlank(message = "Birth country is required")
    private String birthCountry;

    @Pattern(regexp = "^[a-zA-Z0-9]{10}$", message = "CAN must be alphanumeric and exactly 10 characters")
    private String cardAccessNumber;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Face image color is required")
    private String faceImageColor;

    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First Name Primary must not contain special characters or numbers")
    private String firstNamePrimary;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Last Name Secondary must not contain special characters or numbers")
    private String lastNameSecondary;

    @NotBlank(message = "First Name (Latin) is required")
    @Size(min = 1, max = 100, message = "First Name (Latin) must be between 1 and 100 characters")
    @Pattern(regexp = "^[\\p{IsLatin} ]*$", message = "First Name Primary (Latin) must contain only latin script characters.")
    private String firstNamePrimaryLatin;

    @NotBlank(message = "Last Name (Latin) is required")
    @Size(min = 1, max = 100, message = "Last Name (Latin) must be between 1 and 100 characters")
    @Pattern(regexp = "^[\\p{IsLatin} ]*$", message = "Last Name Secondary (Latin) must contain only latin script characters.")
    private String lastNameSecondaryLatin;

    @NotBlank(message = "National ID is required")
    @Pattern(regexp = "^[a-zA-Z0-9]{10}$", message = "National ID must be alphanumeric and exactly 10 characters")
    private String nationalUid;

    @NotBlank(message = "Nationality is required")
    private String nationality;

    private String compassId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuanceDate;
}