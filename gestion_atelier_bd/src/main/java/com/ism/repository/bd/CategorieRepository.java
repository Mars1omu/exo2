package com.ism.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ism.entities.Categorie;
import com.ism.repository.ITables;

public class CategorieRepository implements ITables<Categorie>  {
   private final String SQLINSERT="INSERT INTO `categories` (`libelle`) VALUES (?)";
   private final String SQLALL="select * from categories ";
   private final String SQL_UPDATE = "UPDATE categories SET libelle = ? WHERE id = ?";
   private final String SQL_FIND_BY_ID = "SELECT * FROM categories WHERE id = ?";
   private final String SQL_DELETE = "DELETE FROM categories WHERE id = ?";
   private final String SQL_INDEX_OF = "SELECT id FROM categories";
   private DataBase dataBase;
 

    public CategorieRepository(DataBase dataBase) {
      this.dataBase = dataBase;
      dataBase.connection();

   }

    @Override
    public int insert(Categorie data) {
          int id=0;
                  try {
                      dataBase.getPs().setString(1, data.getLibelle());
                      id= dataBase.executeUpdate(SQLINSERT);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            return id; 
    }

    @Override
    public int update(Categorie data) {
        try {
            PreparedStatement preparedStatement = dataBase.getPs();
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setInt(2, data.getId());
            return dataBase.executeUpdate(SQL_UPDATE);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public ArrayList<Categorie> findAll() {
        ArrayList<Categorie> cateList=new ArrayList<>();
           try  {
            ResultSet res = dataBase.executeSelect(SQLALL);
               while(res.next()){
                Categorie categorie=new Categorie(res.getInt("id"),res.getString("libelle"));
               cateList.add(categorie);
              }
            } catch (SQLException e) {
          
            e.printStackTrace();
          }
            return cateList;
    
    }

    @Override
    public Categorie findById(int id) {
        try {
            PreparedStatement preparedStatement = dataBase.getPs();
            preparedStatement.setInt(1, id);
            ResultSet resultSet = dataBase.executeSelect(SQL_FIND_BY_ID);
            if (resultSet.next()) {
                return new Categorie(resultSet.getInt("id"), resultSet.getString("libelle"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(int id) {
        try {
            PreparedStatement preparedStatement = dataBase.getPs();
            preparedStatement.setInt(1, id);
            return dataBase.executeUpdate(SQL_DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int indexOf(int id) {
        try {
            ResultSet resultSet = dataBase.executeSelect(SQL_INDEX_OF);
            int index = 0;
            while (resultSet.next()) {
                if (resultSet.getInt("id") == id) {
                    return index;
                }
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}
