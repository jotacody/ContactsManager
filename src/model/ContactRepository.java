package model;

import model.db.DB;
import model.db.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    List<Contact> list = new ArrayList<>();
    private Connection conn;

    public ContactRepository(Connection conn) {
        this.conn = conn;
    }

    public List<Contact> getList() {
        return list;
    }

    public void setList(List<Contact> list) {
        this.list = list;
    }

    public Integer newId(){
        Integer newId = 1;
        for (Contact c : list){
            if (c.getId().equals(newId)){
                newId++;
            }
        }
        return newId;
    }

    public void insert(Contact c){
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO contact " +
                            "(name, email, phone ) " +
                            "VALUES " +
                            "(?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, c.getName());
            st.setString(2, c.getEmail());
            st.setString(3, c.getPhone());

            int row = st.executeUpdate();
            rs = st.getGeneratedKeys();

            if (row > 0){
                if (rs.next()){
                    int id = rs.getInt(1);
                    c.setId(id);
                }
            }


        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    public void delete(Integer id){
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement(
                    "DELETE FROM contact\n" +
                            "WHERE id = ?;"
            );
            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }
    }

    public void updateName(Integer id, String s){
        for (Contact c : list){
            if (c.getId().equals(id)){
                c.setName(s);
            }
        }
    }

    public void updateEmail(Integer id, String s){
        for (Contact c : list){
            if (c.getId().equals(id)){
                c.setEmail(s);
            }
        }
    }

    public void updatePhone(Integer id, String s){
        for (Contact c : list){
            if (c.getId().equals(id)){
                c.setPhone(s);
            }
        }
    }
}
