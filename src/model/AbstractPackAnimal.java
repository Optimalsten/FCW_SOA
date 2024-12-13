package model;

import java.time.LocalDate;

// Абстрактное вьючное животное
public abstract class AbstractPackAnimal extends AbstractAnimal {
    public static final int DEFAULT_CARRY_CARGO = 100;

    // Грузоподъемность животного, кг
    private  int CarryCargo;

    public AbstractPackAnimal(String name, LocalDate birthDate) {
        super(name, birthDate);
        this.CarryCargo = DEFAULT_CARRY_CARGO;
    }

    public int getCarryCargo() {
        return CarryCargo;
    }

    public void setCarryCargo(int CarryCargo) {
        this.CarryCargo = CarryCargo;
    }
}