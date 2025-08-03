package org.bygolf.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Position of interest cannot be blank")
    private String positionOfInterest;

    public Subscriber() {
    }

    public Subscriber(String name, String city, String positionOfInterest) {
        this.name = name;
        this.city = city;
        this.positionOfInterest = positionOfInterest;
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

    public @NotBlank(message = "City cannot be blank") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City cannot be blank") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Position of interest cannot be blank") String getPositionOfInterest() {
        return positionOfInterest;
    }

    public void setPositionOfInterest(@NotBlank(message = "Position of interest cannot be blank") String positionOfInterest) {
        this.positionOfInterest = positionOfInterest;
    }
}
