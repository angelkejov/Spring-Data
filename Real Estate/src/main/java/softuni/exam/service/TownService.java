package softuni.exam.service;

import org.xml.sax.SAXException;
import softuni.exam.models.entity.Town;

import java.io.IOException;

public interface TownService {

    boolean areImported();

    String readTownsFileContent() throws IOException;
	
	String importTowns() throws IOException, SAXException;

    Town findByTownName(String townName);
}
