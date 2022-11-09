package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentSeedDTO;
import softuni.exam.models.dto.TownSeedDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

@Service
public class TownServiceImpl implements TownService {
    private static final String TOWNS_FILE_PATH = "src/main/resources/files/json/towns.json";
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    public TownServiceImpl(TownRepository townRepository, Gson gson, ModelMapper modelMapper, Validator validator) {
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
    }


    @Override
    public boolean areImported() {
        return townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(Path.of(TOWNS_FILE_PATH));
    }

    @Override
    public String importTowns() throws IOException {
        TownSeedDTO dto = new TownSeedDTO();
        Set<ConstraintViolation<TownSeedDTO>> errors = validator.validate(dto);
        if (errors.isEmpty()){
            Optional<Town> byFirstName = this.townRepository.getTownByName(dto.getTownName());
            if (byFirstName.isEmpty()){
                Town town = this.modelMapper.map(dto, Town.class);
                this.townRepository.save(town);
                return String.format("Successfully imported agent - %s %d",town.getName(), town.getPopulation());

            }else {
                return "Invalid town";
            }
        }else {
            return "Invalid town";
        }
    }
}
