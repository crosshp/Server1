package com.springapp.mvc.Entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int  id;

    @ManyToOne
    @JoinColumn(name ="idDish")
    private Dish dish;

    @Column(name = "comment")
    private String comment;

    public Comment(String comment, Dish dish){
        this.comment = comment;
        this.dish = dish;
    }
    public Comment(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    /* public int getIdDish() {
            return idDish;
        }

        public void setIdDish(int idDish) {
            this.idDish = idDish;
        }
    */
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
