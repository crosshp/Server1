package com.springapp.mvc.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @Column(name = "idDish")
    @GeneratedValue
    private int id;

    @Column(name = "nameOfDish")
    private String nameOfDish;

    @Column(name = "href")
    private String href;

    @Column(name = "img")
    private String img;

    @Column(name = "steps")
    private String steps;

    @Column(name = "ingridients")
    private String ingredients;
//------- Dish Ingridient
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="dishingredient",
            joinColumns={@JoinColumn(name="idDish")},
            inverseJoinColumns={@JoinColumn(name="idIngridient")})
    private Set<Ingredient> ingredientsSet = new HashSet<Ingredient>();

    public Set<Ingredient> getIngredientsSet() {
        return ingredientsSet;
    }

    public void setIngredientsSet(Set<Ingredient> ingredientsSet) {
        this.ingredientsSet = ingredientsSet;
    }
//-------

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comment")
    private Set<Comment> comments;

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
/*//------

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category")
    private Set<Category> categories;

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
//------------*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

}
