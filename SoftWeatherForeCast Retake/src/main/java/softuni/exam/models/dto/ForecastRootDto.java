package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastRootDto {
    @XmlElement(name = "forecast")
    private Set<ForecastDto> playerSeedDtos;

    public Set<ForecastDto> getPlayerSeedDtos() {
        return playerSeedDtos;
    }

    public void setPlayerSeedDtos(Set<ForecastDto> playerSeedDtos) {
        this.playerSeedDtos = playerSeedDtos;
    }
}
