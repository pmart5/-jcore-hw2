package com.pmart5a.jcorehw2;

import java.util.*;
import static com.pmart5a.jcorehw2.Education.*;
import static com.pmart5a.jcorehw2.Sex.*;

public class MainPopulationCensus {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long numberOfMinors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.printf("Количество несовершеннолетних: %d.\n", numberOfMinors);

        List<String> listFamilyOfConscripts = persons.stream()
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 28)
                .map(Person::getFamily)
                .toList();
        System.out.println("Список фамилий призывников:");
        listFamilyOfConscripts.forEach(System.out::println);

        List<Person> listOfAbleBodiedPeopleWithHigherEducation = persons.stream()
                .filter(person -> person.getEducation() == HIGHER)
                .filter(person -> person.getAge() > 17)
                .filter(person -> (person.getSex() == WOMAN && person.getAge() < 61) || (person.getSex() == MAN &&
                        person.getAge() < 66))
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println("Список потенциально работоспособных людей с высшим образованием:");
        listOfAbleBodiedPeopleWithHigherEducation.forEach(System.out::println);
    }
}
