package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.dto.PlayersSeedDto;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.ValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class PlayerServiceImpl implements PlayerService {
    private static final String PLAYERS_FILE_PATH = "src/main/resources/files/json/players.json";
    private final PlayerRepository playerRepository;
    private final Gson gson;
    private final ValidatorUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, Gson gson, ValidatorUtil validationUtil, ModelMapper modelMapper, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.teamRepository = teamRepository;
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder result = new StringBuilder();

        Arrays.stream(this.gson.fromJson(readPlayersJsonFile(), PlayersSeedDto[].class))
                .filter(agentSeedDto -> validateData(agentSeedDto, result))
                .map(this::mapToEntity)
                .forEach(this.playerRepository::save);

        return result.toString().trim();
    }

    private Player mapToEntity(PlayersSeedDto playersSeedDto) {
        //        player.setTeam(this.townService.findByTownName(playersSeedDto.getTeam()));
        return this.modelMapper.map(playersSeedDto, Player.class);
    }

    private boolean validateData(PlayersSeedDto playersSeedDto, StringBuilder result) {
        if (!this.validationUtil.isValid(playersSeedDto)) {
            result.append("Invalid player").append(System.lineSeparator());
            return false;
        }

        return true;
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return Files.readString(Path.of(PLAYERS_FILE_PATH));
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        //TODO Implement me
        return "";
    }

    @Override
    public String exportPlayersInATeam() {
        //TODO Implement me
        return "";
    }
}
