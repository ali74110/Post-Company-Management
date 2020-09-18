import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        List<Parcel> parcels = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        mainMenu(customers,parcels,cities);
    }

    static void mainMenu(List<Customer> customers, List<Parcel> parcels, List<City> cities) {
        int choice;
        boolean end = false;
        while (!end){
            System.out.println("<< Menu >>");
            System.out.println("1.Customers");
            System.out.println("2.Cities");
            System.out.println("3.Parcels");
            System.out.println("4.Exit");
            choice = scanRightChoice(4);
            switch (choice){
                case 1 : Customer.customersMenu(customers); break;
                case 2 : City.citiesMenu(cities); break;
                case 3 : Parcel.parcelsMenu(customers,parcels,cities); break;
                case 4 : end=true; break;
            }
        }
    }

    static void goBack(){
        Scanner scanner = new Scanner(System.in);
        int choice = 1;
        while (choice!=0){
            System.out.println("Enter 0 to go back:");
            choice = scanner.nextInt();
        }
    }

    public static int scanRightChoice(int limitation){
        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true){
            System.out.println("Enter a number: ");
            choice = scanner.nextInt();
            if (choice>0 && choice<=limitation){
                break;
            }else {
                System.out.println("wrong number!");
            }
        }
        return choice;
    }
}
