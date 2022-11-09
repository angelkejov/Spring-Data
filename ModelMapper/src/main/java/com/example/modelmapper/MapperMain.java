package com.example.modelmapper;

import com.example.modelmapper.demo.Address;
import com.example.modelmapper.demo.Employee;
import com.example.modelmapper.demo.dto.EmployeeDTO;
import com.example.modelmapper.demo.dto.ManagerDto;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MapperMain {

    public static void main(String[] args) {

//        Address address = new Address(
//                16, "Sportist", "Sofia", "Bulgaria");
//
//        Employee employee = new Employee(
//                "Angel", "Kejov", BigDecimal.TEN, LocalDate.now(), address);
//
//        ModelMapper mapper = new ModelMapper();
//
//        EmployeeDTO employeeDTO = mapper.map(employee, EmployeeDTO.class);
//
//        System.out.println(employeeDTO);

        Address address = new Address(
                3,
                "boris tri",
                "Sofia",
                "Bulgaria"
        );

        Employee manager = new Employee(
                "Mr.",
                "Manager",
                BigDecimal.ZERO,
                LocalDate.now(),
                address,
                true);

        Employee first = new Employee(
                "Mr.",
                "Employee First",
                BigDecimal.ONE,
                LocalDate.now(),
                address,
                true);

        Employee second = new Employee(
                "Mr.",
                "Employee Second",
                BigDecimal.TEN,
                LocalDate.now(),
                address,
                true);


        ModelMapper mapper = new ModelMapper();

        ManagerDto dto = mapper.map(manager, ManagerDto.class);

        System.out.println(dto);

    }

}
