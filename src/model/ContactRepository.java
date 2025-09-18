package model;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {
    List<Contact> list = new ArrayList<>();

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
        list.add(c);
    }

    public void delete(Integer id){
        list.removeIf(t -> t.getId().equals(id));
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
