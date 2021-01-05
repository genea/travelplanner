package com.complexica.travelplanner.controllers;

import com.complexica.travelplanner.entities.itinerary.Itinerary;
import com.complexica.travelplanner.entities.itinerary.ItineraryStep;
import com.complexica.travelplanner.models.DummyStringHolder;
import com.complexica.travelplanner.models.TravelPlan;
import com.complexica.travelplanner.repository.ItineraryCitiesInMemoryStore;
import com.complexica.travelplanner.service.ForecastService;
import com.complexica.travelplanner.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TravelPlannerController {
    private final ForecastService forecastService;
    private final ItineraryCitiesInMemoryStore itineraryCitiesInMemoryStore;
    private final ItineraryService itineraryService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("travelPlan", new TravelPlan());
        model.addAttribute("cities", itineraryCitiesInMemoryStore.getAllCities());
        model.addAttribute("dummyStringHolder", new DummyStringHolder());
        return "main";
    }

    @PostMapping("/addCity")
    public String addCity(@ModelAttribute TravelPlan travelPlan, Model model) {
        forecastService.getForecastInfo(travelPlan);
        model.addAttribute("cities", itineraryCitiesInMemoryStore.getAllCities());
        return "redirect:/";
    }

    @PostMapping("/saveItinerary")
    public String saveItinerary(@ModelAttribute DummyStringHolder dummyStringHolder, Model model) {
        itineraryService.saveItinerary(dummyStringHolder.getValue());
        model.addAttribute("cities", itineraryCitiesInMemoryStore.getAllCities());
        return "redirect:/";
    }

    @GetMapping("/itineraries")
    public String getAllItineraries(Model model) {
        List<Itinerary> allItineraries = itineraryService.getAllItineraries();
        model.addAttribute("itineraries", allItineraries);
        return "itineraryList";
    }

    @GetMapping("/itineraries/{id}")
    public String getItinerary(Model model, @PathVariable Long id) {
        Optional<Itinerary> itineraryOptional = itineraryService.getItineraryById(id);
        List<ItineraryStep> itinerarySteps = itineraryOptional.isPresent() ? new ArrayList<>(itineraryOptional.get().getItinerarySteps()) : new ArrayList<>();
        model.addAttribute("itinerarySteps", itinerarySteps);
        return "itinerary";
    }

}
