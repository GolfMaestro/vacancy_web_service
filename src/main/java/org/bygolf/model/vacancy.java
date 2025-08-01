package org.bygolf.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "vacancies")
public class vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotBlank(message = "Position cannot be blank")
    private String position;

    @NotBlank(message = "Salary cannot be blank")
    @PositiveOrZero(message = "Salary cannot be negative")
    private Integer salary;

    @NotNull(message = "Experience cannot be null")
    private String experience;

    @NotNull(message = "City cannot be null")
    private String city;

    public vacancy(String name, String description, String position, Integer salary, String experience, String city) {
        this.name = name;
        this.description = description;
        this.position = position;
        this.salary = salary;
        this.experience = experience;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name cannot be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") String name) {
        this.name = name;
    }

    public @NotNull(message = "Description cannot be null") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "Description cannot be null") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Position cannot be blank") String getPosition() {
        return position;
    }

    public void setPosition(@NotBlank(message = "Position cannot be blank") String position) {
        this.position = position;
    }

    public @NotBlank(message = "Salary cannot be blank") @PositiveOrZero(message = "Salary cannot be negative") Integer getSalary() {
        return salary;
    }

    public void setSalary(@NotBlank(message = "Salary cannot be blank") @PositiveOrZero(message = "Salary cannot be negative") Integer salary) {
        this.salary = salary;
    }

    public @NotNull(message = "Experience cannot be null") String getExperience() {
        return experience;
    }

    public void setExperience(@NotNull(message = "Experience cannot be null") String experience) {
        this.experience = experience;
    }

    public @NotNull(message = "City cannot be null") String getCity() {
        return city;
    }

    public void setCity(@NotNull(message = "City cannot be null") String city) {
        this.city = city;
    }
}
