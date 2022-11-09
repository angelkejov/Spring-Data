package com.example.football.service.impl;

import com.example.football.models.dto.ImportTeamsDto;
import com.example.football.models.dto.ImportTownsDto;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.service.TeamService;
import com.example.football.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class TeamServiceImpl implements TeamService {
    private static final String FILE_PATH = "src/main/resources/files/json/teams.json";

    private final TeamRepository teamRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, ModelMapper modelMapper, Gson gson, ValidationUtil validationUtil) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importTeams() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(gson.fromJson(readTeamsFileContent(), ImportTeamsDto[].class))
                .filter(teamSeedDto -> {
                    boolean isValid = validationUtil.isValid(teamSeedDto);
                    stringBuilder.append(isValid ? String.format("Successfully imported Team %s - %d%n",
                            teamSeedDto.getName(), teamSeedDto.getFanBase()): String.format("Invalid Team%n"));

                    return isValid;
                })
                .map(townSeedDto -> modelMapper.map(townSeedDto, Team.class))
                .forEach(teamRepository::save);


        return stringBuilder.toString();
    }

    @Override
    public Team getById(Long id) {
        return this.teamRepository.findById(id).orElse(null);
    }

    @Override
    public Team getByName(String name) {
        return this.teamRepository.findFirstByName(name);
    }

}
