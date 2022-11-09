package softuni.exam.models.dto;

import softuni.exam.models.entity.Forecast;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalTime;

@XmlRootElement(name = "forecast")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastDto {

    @XmlElement(name = "day_of_week")
    @NotNull
    private String dayOfWeek;

    @XmlElement(name = "max_temperature")
    @NotNull
    private float maxTemperature;

    @XmlElement(name = "min_temperature")
    @NotNull
    private float minTemperature;

    @XmlElement(name = "sunrise")
    @NotNull
    private LocalTime sunrise;

    @XmlElement(name = "sunset")
    @NotNull
    private LocalTime sunset;

    @XmlElement(name = "city")
    private int city;

    public ForecastDto() {

    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
