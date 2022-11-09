package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCountriesDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.service.CityService;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityServiceImp implements CityService {
    private static final String FILE_PATH = "src/main/resources/files/json/cities.json";

    private final CityRepository cityRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;


    public CityServiceImp(Gson gson, ModelMapper modelMapper, Validator validator, CityRepository cityRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }


    private String importCity(ImportCountriesDto dto) {
        Set<ConstraintViolation<ImportCountriesDto>> errors = this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid country";
        }

        City c = modelMapper.map(dto, City.class);

        if (cityRepository.findByCountryId(c.getCityName()).isPresent()) {
            return "Invalid country";
        }
        cityRepository.save(c);
        return String.format("Successfully imported city %s - %d%n", c.getCityName(), c.getPopulation());
    }

    @Override
    public String importCities() throws IOException {
        String json = this.readCitiesFileContent();
        ImportCountriesDto[] country = this.gson.fromJson(json, ImportCountriesDto[].class);

        return Arrays
                .stream(country)
                .map(this::importCity)
                .collect(Collectors.joining("\n"));

    }
}
