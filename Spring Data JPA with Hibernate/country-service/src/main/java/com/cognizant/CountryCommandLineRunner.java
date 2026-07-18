package com.cognizant;

import com.cognizant.model.Country;
import com.cognizant.service.CountryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryCommandLineRunner implements CommandLineRunner {

    private final CountryService countryService;

    public CountryCommandLineRunner(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(String... args) {
        Country country = countryService.getCountry("IN");
        System.out.println("Country by code: " + country);

        List<Country> countries = countryService.searchCountries("Ind");
        System.out.println("Countries by partial name: " + countries);
    }
}