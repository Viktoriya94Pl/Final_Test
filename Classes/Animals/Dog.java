package Classes.Animals;

import Classes.Pets;

public class Dog extends Pets {

    public Dog(String name) {
        super(name);
        super.setKindAnimal("Dog");
    }
}