package softuni.exam.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ImportCountriesDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtil;

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
public class CountryServiceImpl implements CountryService{
    private static final String FILE_PATH = "src/main/resources/files/json/countries.json";

    private final CountryRepository countryRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;


    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;

        this.gson = new GsonBuilder().create();

        this.modelMapper = new ModelMapper();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }



    private String importCountry(ImportCountriesDto dto) {
        Set<ConstraintViolation<ImportCountriesDto>> errors = this.validator.validate(dto);
        if (!errors.isEmpty()) {
            return "Invalid country";
        }

        Country c = modelMapper.map(dto, Country.class);

        if (countryRepository.findByCountryName(c.getCountryName()).isPresent()) {
            return "Invalid country";
        }
        countryRepository.save(c);
        return String.format("Successfully imported country %s - %s%n", c.getCountryName(), c.getCurrency());
    }

    @Override
    public String importCountries() throws IOException {
        String json = this.readCountriesFromFile();
        ImportCountriesDto[] country = this.gson.fromJson(json, ImportCountriesDto[].class);

        return Arrays
                .stream(country)
                .map(this::importCountry)
                .collect(Collectors.joining("\n"));

    }
}
