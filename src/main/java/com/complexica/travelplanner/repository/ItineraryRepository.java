package com.complexica.travelplanner.repository;

import com.complexica.travelplanner.entities.itinerary.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
