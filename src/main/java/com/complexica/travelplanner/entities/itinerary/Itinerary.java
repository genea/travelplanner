package com.complexica.travelplanner.entities.itinerary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "Itinerary")
@Table(name = "Itinerary")
public class Itinerary {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "itinerary", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItineraryStep> itinerarySteps = new HashSet<>();

    public Itinerary() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ItineraryStep> getItinerarySteps() {
        return itinerarySteps;
    }

    public void setItinerarySteps(Set<ItineraryStep> itinerarySteps) {
        this.itinerarySteps = itinerarySteps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addItineraryStep(ItineraryStep itineraryStep){
        this.itinerarySteps.add(itineraryStep);
        itineraryStep.setItinerary(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itinerary)) return false;
        Itinerary itinerary = (Itinerary) o;
        return id.equals(itinerary.id) && name.equals(itinerary.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
