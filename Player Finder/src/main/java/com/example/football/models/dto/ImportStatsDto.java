package com.example.football.models.dto;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stat")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportStatsDto {

    @javax.xml.bind.annotation.XmlElement
    @Positive()
    @NotNull
    private Double passing;
    @javax.xml.bind.annotation.XmlElement
    @Positive
    @NotNull
    private Double shooting;
    @javax.xml.bind.annotation.XmlElement
    @Positive
    @NotNull
    private Double endurance;

    public Double getPassing() {
        return passing;
    }

    public void setPassing(Double passing) {
        this.passing = passing;
    }

    public Double getShooting() {
        return shooting;
    }

    public void setShooting(Double shooting) {
        this.shooting = shooting;
    }

    public Double getEndurance() {
        return endurance;
    }

    public void setEndurance(Double endurance) {
        this.endurance = endurance;
    }
}
