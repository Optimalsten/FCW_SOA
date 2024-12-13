package storage;

import model.AbstractAnimal;
import model.AbstractPackAnimal;
import model.AbstractPet;
import model.implement.*;
import model.Skill;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Реализация интерфейса Storage (хранилище - на HashMap)
public class NurseryStorage implements Storage{
    Map<Integer, AbstractAnimal> dbAnimals = new HashMap<>();

    public NurseryStorage() {
        init();
    }

    private void init(){
        AbstractPet cat = new Cat("Вискас", LocalDate.of(2021, 5, 11));

        int per = cat.getAge();

        cat.learnSkill(new Skill("Лови : мячик"));
        dbAnimals.put(cat.getId(), cat);

        AbstractPet dog = new Dog("Бэлла", LocalDate.of(2020, 5, 12));
        dog.learnSkill(new Skill("Сидеть"));
        dog.learnSkill(new Skill("Фас!"));
        dbAnimals.put(dog.getId(), dog);

        AbstractPet hamster = new Hamster("Толстый", LocalDate.of(2023, 9, 20));
        hamster.learnSkill(new Skill("Бежать : в колесе"));
        dbAnimals.put(hamster.getId(), hamster);

        AbstractPackAnimal horse = new Horse("Шторм", LocalDate.of(2021, 8, 3));
        horse.setCarryCargo(300);
        horse.learnSkill(new Skill("Просить : подъем ноги"));
        dbAnimals.put(horse.getId(), horse);

        AbstractPackAnimal horse2 = new Horse("Зарина", LocalDate.of(2023, 07, 16));
        horse2.setCarryCargo(400);
        horse2.learnSkill(new Skill("Но, пошла!"));
        horse2.learnSkill(new Skill("Поклон!"));
        dbAnimals.put(horse2.getId(), horse2);

        AbstractPackAnimal donkey = new Donkey("Ослик", LocalDate.of(2020, 3, 24));
        donkey.setCarryCargo(500);
        donkey.learnSkill(new Skill("Пошёл / вперед!"));
        dbAnimals.put(donkey.getId(), donkey);

        AbstractPackAnimal camel = new Camel("Сахара", LocalDate.of(2022, 1, 18));
        camel.setCarryCargo(1000);
        camel.learnSkill(new Skill("Лежать!"));
        dbAnimals.put(camel.getId(), camel);
    }
    @Override
    public List<AbstractAnimal> getAllAnimals() {
        List<AbstractAnimal> result = new ArrayList<>();
        for (AbstractAnimal animal: dbAnimals.values()) {
            result.add(animal);
        }
        return result;
    }

    @Override
    public AbstractAnimal getAnimalById(int animalId) {
        return dbAnimals.getOrDefault(animalId, null);
    }

    @Override
    public boolean addAnimal(AbstractAnimal animal) {
        if (dbAnimals.containsKey(animal.getId())) {return false;}
        dbAnimals.put(animal.getId(), animal);
        return true;
    }

    @Override
    public int removeAnimal(AbstractAnimal animal) {
        if (!dbAnimals.containsKey(animal.getId())) {
            return -1;
        }
        AbstractAnimal removed = dbAnimals.remove(animal.getId());
        return removed.getId();
    }
}