package com.complexica.travelplanner.service;

import com.complexica.travelplanner.entities.itinerary.Itinerary;
import com.complexica.travelplanner.repository.ItineraryCitiesInMemoryStore;
import com.complexica.travelplanner.repository.ItineraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItineraryService {
    private final ItineraryRepository itineraryRepository;
    private final ItineraryCitiesInMemoryStore itineraryCitiesInMemoryStore;
    private final ConvertorService convertorService;

    public void saveItinerary(String itineraryName){
        Itinerary itinerary = new Itinerary();
        itinerary.setName(itineraryName);

        convertorService.convert(itinerary, itineraryCitiesInMemoryStore.getAllCities());

        itineraryRepository.save(itinerary);

        itineraryCitiesInMemoryStore.clearStore();
    }

    public List<Itinerary> getAllItineraries(){
        return itineraryRepository.findAll();
    }

    public Optional<Itinerary> getItineraryById(Long id){
        return itineraryRepository.findById(id);
    }
}
