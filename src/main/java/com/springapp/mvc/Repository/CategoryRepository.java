package com.springapp.mvc.Repository;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.springapp.mvc.Entity.Category;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> getAllCategory() {
        return this.sessionFactory.getCurrentSession().createQuery("from Category").list();
    }

    public Category getCategory(int id) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
    }

    public List<Object[]> getDishCategoryById(int id) {
        return (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery("select category.id, category.nameCategory from cook.dish, cook.category, cook.dishcategory where\n" +
                "dishcategory.idDish = dish.idDish and category.id = dishcategory.idCategory and dish.idDish =" + id + ";").list();
    }

    public String getDishCategoryByIdJSON(int id) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (Object[] objects : getDishCategoryById(id)) {
                JSONObject object = new JSONObject();
                object.put("id", objects[0].toString());
                object.put("category", objects[1].toString());
                jsonArray.put(object);
            }
            jsonObject.put("array", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }


    public String getDishByCategoryJSON(int category) {
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>();
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
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
            sql = "select dish.* from dish,category,dishcategory where " +
                    "dishcategory.idDish = dish.idDish and category.id = dishcategory.idCategory and category.id='" + category + "';";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                JSONObject object = new JSONObject();
                object.put("idDish",String.valueOf(rs.getInt("idDish")));
                object.put("nameOfDish", rs.getString("nameOfDish"));
                object.put("href", rs.getString("href"));
                object.put("img", rs.getString("img"));
                object.put("steps", rs.getString("steps"));
                object.put("ingridients", rs.getString("ingridients"));
                jsonArray.put(object);
            }
            jsonObject.put("array",jsonArray);
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
        return jsonObject.toString();
    }

}
