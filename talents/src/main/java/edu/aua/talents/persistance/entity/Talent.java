package edu.aua.talents.persistance.entity;

import edu.aua.talents.persistance.enums.TalentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "talent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Talent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "surname")
    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @Column(name = "email", unique = true)
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number is mandatory")
    @Size(min = 9, max = 12)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TalentStatus talentStatus;

    @Column(name = "cv_file_name")
    private String cvFileName;

    @Column(name = "date_applied")
    private LocalDateTime dateApplied;

    public String getFullName() {
        return this.getName() + "_" + this.getSurname();
    }

}

