package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentSeedDTO;
import softuni.exam.models.dto.ApartmentsDTO;
import softuni.exam.models.dto.ApartmentsRootDTO;
import softuni.exam.models.entity.Agent;
import softuni.exam.models.entity.Apartment;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.service.ApartmentService;
import softuni.exam.util.ValidationUntil;
import softuni.exam.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    private static final String APARTMENT_FILE_PATH = "src/main/resources/files/xml/apartments.xml";
    private final ApartmentRepository apartmentRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final XmlParser parser;
    private ValidationUntil validationUntil;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, Gson gson, ModelMapper modelMapper, Validator validator, XmlParser parser) {
        this.apartmentRepository = apartmentRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.parser = parser;
    }


    @Override
    public boolean areImported() {
        return apartmentRepository.count() > 0;
    }

    @Override
    public String readApartmentsFromFile() throws IOException {
        return Files.readString(Path.of(APARTMENT_FILE_PATH));
    }

    @Override
        public String importApartments() throws IOException, JAXBException {
            JAXBContext context = JAXBContext.newInstance(ApartmentsRootDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ApartmentsRootDTO apartmentRootDTO = (ApartmentsRootDTO) unmarshaller.unmarshal(new FileReader(APARTMENT_FILE_PATH));

            return apartmentRootDTO.getApartments().stream().map(this::importApartment).collect(Collectors.joining("\n"));
        }

        private String importApartment(ApartmentsDTO apartmentDTO) {

            Set<ConstraintViolation<ApartmentsDTO>> errors = validator.validate(apartmentDTO);

            if (errors.isEmpty()){

                Optional<Apartment> town = apartmentRepository.findByTownName(String.valueOf(apartmentDTO.getTown()));

                if (town.isPresent()) {

                    Apartment apartment = new Apartment();

                    ApartmentType apartmentType = apartmentDTO.getApartmentType();
                    double area = apartmentDTO.getArea();

                    apartment.setApartmentType(apartmentType);
                    apartment.setArea((float) area);
                    apartment.setTown(town.get().getTown());


                    apartmentRepository.save(apartment);

                    return String.format("Successfully imported apartment %s - %.2f",apartment.getApartmentType(),apartment.getArea());
                }

                else {
                    return "Invalid Apartment";
                }
            }
            else {
                return "Invalid Apartment";
            }

        }
}
