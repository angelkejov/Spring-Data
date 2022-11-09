package com.example.football.models.dto;

import com.example.football.models.entity.Postition;
import com.sun.istack.NotNull;

import javax.swing.text.Position;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {

    @javax.xml.bind.annotation.XmlElement(name = "first-name")
    @Size(min = 2)
    @NotNull
    private String firstName;
    @XmlElement(name = "last-name")
    @Size(min = 2)
    @NotNull
    private String lastName;
    @javax.xml.bind.annotation.XmlElement
    @Email
    @NotNull
    private String email;
    @javax.xml.bind.annotation.XmlElement(name = "birth-date")
    @NotNull
    private String birthDate;
    @javax.xml.bind.annotation.XmlElement
    @NotNull
    private Postition position;
    @javax.xml.bind.annotation.XmlElement
    @NotNull
    private ImportTownsDto town;
    @javax.xml.bind.annotation.XmlElement
    @NotNull
    private ImportTeamsDto team;
    @javax.xml.bind.annotation.XmlElement
    @NotNull
    private StatIdDto stat;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }



    public StatIdDto getStat() {
        return stat;
    }

    public void setStat(StatIdDto stat) {
        this.stat = stat;
    }

    public ImportTeamsDto getTeam() {
        return team;
    }

    public void setTeam(ImportTeamsDto team) {
        this.team = team;
    }

    public ImportTownsDto getTown() {
        return town;
    }

    public void setTown(ImportTownsDto town) {
        this.town = town;
    }

    public Postition getPosition() {
        return position;
    }

    public void setPosition(Postition position) {
        this.position = position;
    }
}
