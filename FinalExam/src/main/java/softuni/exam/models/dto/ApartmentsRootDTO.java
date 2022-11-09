package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentsRootDTO {

    @XmlElement(name = "town")
    private List<ApartmentsDTO> apartments;

    public List<ApartmentsDTO> getApartments() {
        return apartments;
    }

    public void setApartments(List<ApartmentsDTO> towns) {
        this.apartments = towns;
    }
}
