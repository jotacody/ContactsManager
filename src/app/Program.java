package app;

import model.dao.ContactDao;
import model.db.DBException;
import model.entities.Contact;
import model.dao.ContactDaoImpl;
import model.db.DB;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContactDaoImpl cr = new ContactDaoImpl(DB.getConnection());

        int n;
        do {
            System.out.println("1 = insert, 2 = delete, 3 = update, 4 = quit ");
            n = sc.nextInt();

            switch (n){
                case 1:
                    System.out.print("Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Phone: ");
                    String phone = sc.nextLine();


                    Contact contact = new Contact(name, email, phone);
                    cr.insert(contact);
                    System.out.println("Contact added successfully (ID: " + contact.getId() + ")");
                    break;
                case 2:
                    System.out.print("Id: ");
                    Integer id = sc.nextInt();
                    cr.testId(id);
                    cr.delete(id);
                    break;
                case 3:
                    System.out.print("Id: ");
                    Integer idUpdate = sc.nextInt();
                    cr.testId(idUpdate);
                    System.out.print("Name: ");
                    sc.nextLine();
                    String nameUpdate = sc.nextLine();
                    System.out.print("Email: ");
                    String emailUpdate = sc.nextLine();
                    System.out.print("Phone: ");
                    String phoneUpdate = sc.nextLine();
                    cr.update(idUpdate, nameUpdate, emailUpdate, phoneUpdate);
                    break;
                case 4:
                    for (Contact c : cr.listAll()){
                        System.out.println(c);
                    }
                    break;
            }

        }while (n != 4);

        sc.close();
        DB.closeConnection();

    }
}
