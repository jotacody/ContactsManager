package model.dao;

import model.entities.Contact;

import java.util.List;

public interface ContactDao {
    public void insert(Contact c);
    public void delete(Integer id);
    public void update(Integer id, String name, String email, String phone);
    public List<Contact> listAll();
    public Contact searchContact(List<Contact> list, String s);
    public void testId(Integer id);
}
