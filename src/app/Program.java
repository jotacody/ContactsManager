package app;

import model.Contact;
import model.ContactRepository;
import model.db.DB;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DB db = null;
        ContactRepository cr = new ContactRepository(db.getConnection());

        int n;
        do {
            System.out.println("1 = insert, 2 = delete, 3 = updateName, 4 = updateEmail, 5 = updatePhone, 6 = quit ");
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
                    cr.delete(id);
                    break;
                case 3:
                    System.out.print("Id: ");
                    Integer idUpdate = sc.nextInt();
                    System.out.print("New Name: ");
                    sc.nextLine();
                    String nName = sc.nextLine();
                    cr.updateName(idUpdate,nName);
                    System.out.println(cr.getList());
                    break;
                case 4:
                    System.out.print("Id: ");
                    Integer idEmail = sc.nextInt();
                    System.out.print("New Email: ");
                    sc.nextLine();
                    String newEmail = sc.nextLine();
                    cr.updateEmail(idEmail, newEmail);
                    System.out.println(cr.getList());
                    break;
                case 5:
                    System.out.print("Id: ");
                    Integer idPhone = sc.nextInt();
                    System.out.print("New Phone: ");
                    sc.nextLine();
                    String newPhone = sc.nextLine();
                    cr.updatePhone(idPhone, newPhone);
                    System.out.println(cr.getList());
                    break;
                case 6:
                    break;

                }

        }while (n != 6);

    }
}
