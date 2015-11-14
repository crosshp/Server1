package com.springapp.mvc.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ingridient")
public class Ingredient {
    @Id
    @Column(name = "idIngridient")
    @GeneratedValue
    private int id;

    @Column(name = "nameOFIngridient")
    private String nameOfIngredient;


   /* @ManyToMany(mappedBy="ingredientsSet")
    private Set<Dish> dishSet = new HashSet<Dish>();

    public Set<Dish> getDishSet() {
        return dishSet;
    }

    public void setDishSet(Set<Dish> dishSet) {
        this.dishSet = dishSet;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfIngredient() {
        return nameOfIngredient;
    }

    public void setNameOfIngredient(String nameOfIngredient) {
        this.nameOfIngredient = nameOfIngredient;
    }
}
