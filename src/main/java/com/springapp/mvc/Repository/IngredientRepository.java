package com.springapp.mvc.Repository;

import com.springapp.mvc.Entity.Ingredient;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class IngredientRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Ingredient> getAllIngredient() {
        return this.sessionFactory.getCurrentSession().createQuery("from Ingredient").list();
    }

    public Ingredient getIngredientById(int id) {
        return (Ingredient) sessionFactory.getCurrentSession().get(Ingredient.class, id);
    }
}
