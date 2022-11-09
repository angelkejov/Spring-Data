package softuni.exam.models.dto;

import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentsDTO {

    @XmlElement(name = "apartment-type")
    private ApartmentType apartmentType;

    @XmlElement(name = "area")
    @Size(min = 40)
    private float area;

    @XmlElement(name = "town")
    @NotBlank
    private Town town;

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(ApartmentType apartmentType) {
        this.apartmentType = apartmentType;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
