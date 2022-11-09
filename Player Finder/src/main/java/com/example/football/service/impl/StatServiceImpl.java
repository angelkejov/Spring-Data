package com.example.football.service.impl;

import com.example.football.models.dto.ImportStatsDto;
import com.example.football.models.dto.ImportTeamsDto;
import com.example.football.models.dto.StatSeedRootDto;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import com.example.football.util.ValidationUtil;
import com.example.football.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class StatServiceImpl implements StatService {
    private static final String STATS_FILE_PATH = "src/main/resources/files/xml/stats.xml";
    private final StatRepository statRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public StatServiceImpl(StatRepository statRepository, XmlParser xmlParser, ValidationUtil validationUtil,
                           ModelMapper modelMapper) {
        this.statRepository = statRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(STATS_FILE_PATH));
    }

    @Override
    public String importStats() throws JAXBException, FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();

        xmlParser
                .fromFile(STATS_FILE_PATH, StatSeedRootDto.class)
                .getStatSeedDtoSet()
                .stream()
                .filter(statSeedDto -> {
                    boolean isValid = validationUtil.isValid(statSeedDto);
                    stringBuilder.append(isValid ?
                            String.format("Successfully import Stat %.2f - %.2f - %.2f%n", statSeedDto.getPassing(),
                                    statSeedDto.getShooting(), statSeedDto.getEndurance())
                            : String.format("Invalid Stat%n"));

                    return isValid;
                })
                .map(statSeedDto -> modelMapper.map(statSeedDto, Stat.class))
                .forEach(statRepository::save);

        return stringBuilder.toString();
    }

    @Override
    public Stat getById(Long id) {
        return this.statRepository.findById(id).orElse(null);
    }
}
