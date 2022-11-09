package softuni.exam.service;


import softuni.exam.models.dto.AgentSeedDTO;
import softuni.exam.repository.AgentRepository;

import java.io.IOException;

public interface AgentService {

    boolean areImported();

    String readAgentsFromFile() throws IOException;

	String importAgents() throws IOException;

}
