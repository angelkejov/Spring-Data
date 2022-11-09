package softuni.exam.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.xml.transform.Source;

public class ImportTownDTO implements Source {

    @Min(2)
    private String townName;

    @Positive
    private int population;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public void setSystemId(String systemId) {
    }

    @Override
    public String getSystemId() {
        return null;
    }
}
