package com.springapp.mvc.Repository;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.springapp.mvc.Entity.Dish;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
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

    public List<Dish> getRandom20Dishes() {
        List<Integer> setForIndex = new ArrayList<Integer>();
        while (setForIndex.size() < 20) {
            setForIndex.add((int) (Math.random() * 1371) + 1);
        }
        Iterator iterator = setForIndex.iterator();
        List<Dish> dishSet = new ArrayList<Dish>();
        while (iterator.hasNext()) {
            dishSet.add(getDishById((Integer) iterator.next()));
        }
        return dishSet;
    }

    public ArrayList<List<String>> getRandom20DishesJSON() {
        ArrayList<List<String>> resultSet = new ArrayList<List<String>>();
        List<Dish> dishSet = getRandom20Dishes();
        Iterator iterator = dishSet.iterator();
        while (iterator.hasNext()) {
            Dish dish = (Dish) iterator.next();
            List<String> set = new ArrayList<String>();
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

    public ArrayList<List<String>> getAllDishesJSON() {
        ArrayList<List<String>> resultSet = new ArrayList<List<String>>();
        List<Dish> dishSet = getAllDish();
        Iterator iterator = dishSet.iterator();
        while (iterator.hasNext()) {
            Dish dish = (Dish) iterator.next();
            List<String> set = new ArrayList<String>();
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

    public ArrayList<ArrayList<String>> getDishByIngredientsJSON(String ingredients) {

        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        String DB_URL = "jdbc:mysql://127.0.0.1:3306/cook";
        String USER = "root";
        String PASS = "root";
        Connection conn = null;
        Statement stmt = null;
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "select cook.dish.* from cook.dish,cook.dishingridient,cook.ingridient \n" +
                    "where cook.dish.idDish = dishingridient.idDish \n" +
                    "and dishingridient.idIngridient = ingridient.idIngridient\n" +
                    "and ingridient.idIngridient in("+ingredients+
                    ") \n group by cook.dish.idDish\n" +
                    "order by cook.dish.countOfIngredient";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(String.valueOf(rs.getInt("idDish")));
                arrayList.add(rs.getString("nameOfDish"));
                arrayList.add(rs.getString("href"));
                arrayList.add(rs.getString("img"));
                arrayList.add(rs.getString("steps"));
                arrayList.add(rs.getString("ingridients"));
                arrayLists.add(arrayList);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return arrayLists;
    }
}
