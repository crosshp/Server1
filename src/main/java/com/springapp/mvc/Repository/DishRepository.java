package com.springapp.mvc.Repository;

import com.springapp.mvc.Entity.Dish;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
@Transactional
public class DishRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Dish> getAllDish() {
        return this.sessionFactory.getCurrentSession().createQuery("from Dish").list();
    }

    public Dish getDishById(int id) {
        return (Dish) sessionFactory.getCurrentSession().get(Dish.class, id);
    }

    public Set<Dish> getRandom20Dishes() {
        Set<Integer> setForIndex = new HashSet<Integer>();
        while (setForIndex.size() < 20) {
            setForIndex.add((int) (Math.random() * 1371) + 1);
        }
        Iterator iterator = setForIndex.iterator();
        Set<Dish> dishSet = new HashSet<Dish>();
        while (iterator.hasNext()) {
            dishSet.add(getDishById((Integer) iterator.next()));
        }
        return dishSet;
    }

    public ArrayList<Set<String>> getRandom20DishesJSON() {
        ArrayList<Set<String>> resultSet = new ArrayList<Set<String>>();
        Set<Dish> dishSet = getRandom20Dishes();
        Iterator iterator = dishSet.iterator();
        while (iterator.hasNext()) {
            Dish dish = (Dish) iterator.next();
            Set<String> set = new HashSet<String>();
            set.add(String.valueOf(dish.getId()));
            set.add(dish.getNameOfDish());
            set.add(dish.getHref());
            set.add(dish.getImg());
            set.add(dish.getSteps());
            set.add(dish.getIngredients());
            resultSet.add(set);
        }
        return resultSet;
    }
}
