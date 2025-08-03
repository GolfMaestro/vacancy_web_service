package org.bygolf.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Position of interest cannot be blank")
    private String position;

    public Subscriber() {
    }

    public Subscriber(String email, String name, String city, String position) {
        this.email = email;
        this.name = name;
        this.city = city;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public @NotBlank(message = "Name cannot be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name cannot be blank") String name) {
        this.name = name;
    }

    public @NotBlank(message = "City cannot be blank") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City cannot be blank") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Position of interest cannot be blank") String getPosition() {
        return position;
    }

    public void setPosition(@NotBlank(message = "Position of interest cannot be blank") String position) {
        this.position = position;
    }
}
