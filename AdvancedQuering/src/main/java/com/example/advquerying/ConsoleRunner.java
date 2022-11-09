package com.example.advquerying;

import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final ShampooRepository shampooRepository;

    @Autowired
    public ConsoleRunner(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);

        String strSize = sc.nextLine();

        Size size = Size.valueOf(strSize);
        Label label = new Label(labelId);

        Label labelId = String.valueOf(label);
        this.shampooRepository.getShampooByLabelOrSizeOrderByPrice((Label) labelId, size)
                .forEach(System.out::println);
    }
}
