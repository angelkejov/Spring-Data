package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastDto;
import softuni.exam.models.dto.ForecastRootDto;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ForecastServiceImpl implements ForecastService {
    private static final String FILE_PATH = "src/main/resources/files/xml/forecasts.xml";

    private final ForecastRepository forecastRepository;
    private final XmlParser parser;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final ValidationUtil validationUtil;

    public ForecastServiceImpl(ForecastRepository forecastRepository, ModelMapper modelMapper, XmlParser parser, ValidationUtil validationUtil) {
        this.forecastRepository = forecastRepository;
        this.parser = parser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();;
    }


    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        parser
                .fromFile(FILE_PATH, ForecastRootDto.class)
                .getPlayerSeedDtos()
                .stream()
                .filter(statSeedDto -> {
                    boolean isValid = validationUtil.isValid(statSeedDto);
                    stringBuilder.append(isValid ?
                            String.format("Successfully import forecast %s - %.2f", statSeedDto.getDayOfWeek(),
                                    (statSeedDto.getMaxTemperature()))
                            : String.format("Invalid Stat%n"));

                    return isValid;
                })
                .map(statSeedDto -> modelMapper.map(statSeedDto, Forecast.class))
                .forEach(forecastRepository::save);

        return stringBuilder.toString();
    }

    @Override
    public String exportForecasts() {
        return null;
    }
}
