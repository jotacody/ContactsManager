package model.dao;

import model.entities.Contact;
import model.db.DB;
import model.db.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {
    private Connection conn;

    public ContactDaoImpl(Connection conn) {
        this.conn = conn;
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
        }finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
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
        }finally {
            DB.closeStatement(st);
        }
    }

    public void update(Integer id, String name, String email, String phone){
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE contact SET name=?, email=?, phone=? WHERE id=?"
            );
            st.setString(1 , name);
            st.setString(2 , email);
            st.setString(3 , phone);
            st.setInt(4, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    public List<Contact> listAll(){
        List<Contact> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM contact ORDER BY name ASC; ");
            rs = st.executeQuery();

            while (rs.next()){
                Contact c = new Contact();

                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));

                list.add(c);
            }
        } catch (SQLException e){
            throw new DBException(e.getMessage());
        }

        return list;
    }

    public Contact searchContact(List<Contact> list, String s){
        for (Contact c : list) {
            if (c.getName().equals(s) || c.getEmail().equals(s) || c.getPhone().equals(s)) {
                return c;
            }
        }
        throw new RuntimeException("There is no contact with that name, email or phone!");
    }

    public void testId(Integer id){
        int cont = 0;
        for (Contact c : listAll()){
            if (c.getId().equals(id)){
                cont++;
            }
        }
        if (cont == 0){
            throw new DBException("There is no contact with this id!");
        }
    }
}
