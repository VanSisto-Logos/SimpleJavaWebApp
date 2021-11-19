package com.vansisto.repositories;

import com.vansisto.entities.Pet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PetRepository {
    private List<Pet> pets = new ArrayList<>();

    public void save(Pet pet) {
        pets.add(pet);
    }
}
