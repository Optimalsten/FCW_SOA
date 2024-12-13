package storage;

import model.AbstractAnimal;

import java.util.List;

public interface Storage {

    // Возвращает список животных из хранилища (@return Список животных)
    List<AbstractAnimal> getAllAnimals();

    // Возвращает животное по его id из хранилища
    // (@return - животное или null, если с таким id нет)
    AbstractAnimal getAnimalById(int animalId);

    // Добавляет животное в хранилище
    // (@return - true в случае успеха, иначе - false)
    boolean addAnimal(AbstractAnimal animal);

    // Удаление/выписывание животного из хранилища
    // (@return - id удаленного, или -1, если удаляемого нет в хранилище)
    int removeAnimal(AbstractAnimal animal);
}