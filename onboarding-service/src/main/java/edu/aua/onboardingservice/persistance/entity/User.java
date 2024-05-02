package edu.aua.onboardingservice.persistance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
@Data
@NoArgsConstructor
public class User extends AbstractAuditAwareBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "jira_display_name")
    private String displayName;

    @Column(name = "jira_account_id")
    @JsonProperty(value = "accountId")
    private String accountId;
}
