package com.springapp.mvc.Entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int  id;

    @Column(name ="idDish")
    private int idDish;

    @Column(name = "comment")
    private String comment;



    public Comment(String comment, int idDish){
        this.comment = comment;
        this.idDish = idDish;

    }
    public Comment(){

    }

    public int getIdDish() {
        return idDish;
    }

    public void setIdDish(int idDish) {
        this.idDish = idDish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
