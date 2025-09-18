package model;

import model.db.DB;
import model.db.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    private Connection conn;

    public ContactRepository(Connection conn) {
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

    public void updateName(Integer id, String s){
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE contact " +
                            "SET name = ? " +
                            "WHERE id = ?;"
            );
            st.setString(1, s);
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    public void updateEmail(Integer id, String s){
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE contact " +
                            "SET email = ? " +
                            "WHERE id = ?;"
            );
            st.setString(1, s);
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        }finally {
            DB.closeStatement(st);
        }
    }

    public void updatePhone(Integer id, String s){
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE contact " +
                            "SET phone = ? " +
                            "WHERE id = ?;"
            );
            st.setString(1, s);
            st.setInt(2, id);
            st.executeUpdate();
        }catch (SQLException e){
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    public List<Contact> loadFromDb(){
        List<Contact> list = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM contact");
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
}
