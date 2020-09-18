import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Customer {
    private String name;
    private long nationalId;

    public Customer(String name, long nationalId) {
        this.name = name;
        this.nationalId = nationalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNationalId() {
        return nationalId;
    }

    public void setNationalId(long nationalId) {
        this.nationalId = nationalId;
    }

    @Override
    public String toString() {
        return  name +" | "+ nationalId ;
    }
    void edit(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("which part do you want to edit:");
        System.out.println("1.name");
        System.out.println("2.national id:");
        System.out.println("3.back");
        int editChoice = Main.scanRightChoice(3);
        if (editChoice==1){
            System.out.println("new name:");
            String name = scanner.next();
            this.setName(name);
        }else if (editChoice==2){
            System.out.println("new national id:");
            long id;
            try {
                id = scanner.nextLong();
            }catch (Exception e){
                System.err.println("just numbers!!!(edit customer later)");
                Random random = new Random();
                id = random.nextLong();
            }
            this.setNationalId(id);
        }
    }

    static void customersMenu(List<Customer> customers) {
        int choice;
        boolean end = false;
        while (!end){
            System.out.println("<< Customers Menu >>");
            System.out.println("1.add customer");
            System.out.println("2.edit customer");
            System.out.println("3.show customers");
            System.out.println("4.search");
            System.out.println("5.Back");
            choice = Main.scanRightChoice(5);
            switch (choice){
                case 1 : addCustomer(customers); break;
                case 2 : editCustomer(customers); break;
                case 3 : showCustomers(customers); break;
                case 4 : searchCustomers(customers);break;
                case 5 : end=true; break;
            }
        }
    }

    static void addCustomer(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        long id ;
        System.out.println("add a customer:");
        System.out.println("1.name:");
        String name = scanner.next();
        System.out.println("2.national id:");
        try {
            id = scanner.nextLong();
        }catch (Exception e){
            System.err.println("just numbers!!!(edit customer later)");
            Random random = new Random();
            id = random.nextLong();
        }
        Customer customer = new Customer(name,id);
        customers.add(customer);
    }

    static void editCustomer(List<Customer> customers) {
        System.out.println("choose a customer:");
        showCustomers(customers);
        System.out.println("your choice:");
        int customerChoice = Main.scanRightChoice(customers.size());
        Customer customer = customers.get(customerChoice-1);
        System.out.println(customer.getName()+" selected.");
        customer.edit();
    }

    static void showCustomers(List<Customer> customers) {
        for (int i = 1; i <= customers.size(); i++) {
            System.out.println(i+"."+customers.get(i-1));
        }
    }

    static void searchCustomers(List<Customer> customers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer name:");
        String searchName = scanner.next();
        List<Customer> wantedCustomers = new ArrayList<>();
        for (Customer customer : customers){
            if (customer.getName().contains(searchName)){
                wantedCustomers.add(customer);
            }
        }
        showCustomers(wantedCustomers);
        Main.goBack();
    }
}
