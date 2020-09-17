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
                case 1 : customersMenu(customers); break;
                case 2 : citiesMenu(customers,parcels,cities); break;
                case 3 : parcelsMenu(customers,parcels,cities); break;
                case 4 : end=true; break;
            }
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
            choice = scanRightChoice(5);
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
        int customerChoice = scanRightChoice(customers.size());
        Customer customer = customers.get(customerChoice-1);
        System.out.println(customer.getName()+" selected.");
        customer.edit();
    }

    private static void showCustomers(List<Customer> customers) {
        for (int i = 1; i <= customers.size(); i++) {
            System.out.println(i+"."+customers.get(i-1));
        }
    }

    static void searchCustomers(List<Customer> customers) {

    }


    static void citiesMenu(List<Customer> customers, List<Parcel> parcels, List<City> cities) {
        int choice;
        boolean end = false;
        while (!end){
            System.out.println("<< Cities Menu >>");
            System.out.println("1.add city");
            System.out.println("2.edit city");
            System.out.println("3.show cities");
            System.out.println("4.Back");
            choice = scanRightChoice(4);
            switch (choice){
                case 1 : addCity(cities); break;
                case 2 : editCity(cities); break;
                case 3 : showCities(cities); break;
                case 4 : end = true; break;
            }
        }
    }

    static void addCity(List<City> cities) {
        Scanner scanner = new Scanner(System.in);
        double latitude,longitude;
        String name;
        System.out.println("add a city:");
        System.out.println("1.name");
        name = scanner.next();
        System.out.println("2.latitude:");
        latitude = scanner.nextDouble();
        System.out.println("3.longitude:");
        longitude = scanner.nextDouble();
        City city = new City(name,latitude,longitude);
        cities.add(city);
    }

    static void editCity(List<City> cities) {
        System.out.println("choose a city:");
        showCities(cities);
        System.out.println("your choice:");
        int cityChoice = scanRightChoice(cities.size());
        City city = cities.get(cityChoice-1);
        System.out.println(city.getName()+" selected.");

    }

    static void showCities(List<City> cities) {
        for (int i = 1; i <= cities.size(); i++) {
            System.out.println(i+"."+cities.get(i-1));
        }
    }


    static void parcelsMenu(List<Customer> customers, List<Parcel> parcels, List<City> cities) {
        int choice;
        boolean end = false;
        while (!end){
            System.out.println("<< parcels Menu >>");
            System.out.println("1.add parcel");
            System.out.println("2.show parcels(by filters)");
            System.out.println("3.parcel tracking");
            System.out.println("4.send parcels");
            System.out.println("5.update");
            System.out.println("6.search");
            System.out.println("7.Back");
            choice = scanRightChoice(7);
            switch (choice){
                case 1 : addParcel(customers,parcels,cities); break;
                case 2 : showParcels(parcels); break;
                case 3 : trackParcel(parcels); break;
                case 4 : sendParcels(parcels); break;
                case 5 : updateParcels(parcels,cities); break;
                case 6 : searchParcel(customers,cities); break;
                case 7 : end = true; break;
            }
        }
    }

    static void addParcel(List<Customer> customers, List<Parcel> parcels, List<City> cities) {
        if (customers.size()<2 || cities.size()<2){
            System.out.println("at least add 2 cities & 2 customers!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        String name;
        Customer sender , receiver;
        City from , to , current;
        Date sendingDate , receivingDate ;
        int weight;
        SendingWay sendingWay;
        PostType postType ;
        System.out.println("add a parcel:");
        System.out.println("name:");
        name = scanner.next();

        System.out.println("post type:");
        System.out.println("1.normal");
        System.out.println("2.special");
        int postTypeChoice = scanRightChoice(2);
        if (postTypeChoice == 1) {
            postType = PostType.NORMAL;
        } else {
            postType = PostType.SPECIAL;
        }

        System.out.println("weight:");
        weight = scanner.nextInt();

        System.out.println("choose sender:");
        showCustomers(customers);
        sender = customers.get(scanRightChoice(customers.size())-1);

        while (true){
            System.out.println("choose receiver:");
            showCustomers(customers);
            receiver = customers.get(scanRightChoice(customers.size())-1);
            if (!sender.equals(receiver)){
                break;
            }else {
                System.out.println("sender chosen!");
            }
        }

        System.out.println("choose first city:");
        showCities(cities);
        from = current = cities.get(scanRightChoice(cities.size())-1);

        while (true){
            System.out.println("choose destination city:");
            showCities(cities);
            to = cities.get(scanRightChoice(cities.size())-1);
            if (!from.equals(to)){
                break;
            }else {
                System.out.println("first city chosen!");
            }
        }

        System.out.println("choose the sending way:");
        while (true){
            System.out.println("1.airway");
            System.out.println("2.seaway");
            System.out.println("3.road");
            int wayChoice = scanRightChoice(3);
            switch (wayChoice){
                case 2 : sendingWay = SendingWay.SEAWAY ;break;
                case 3 : sendingWay = SendingWay.ROAD ;break;
                default: sendingWay = SendingWay.AIRWAY;break;
            }
            if (from.getCoordinate().calculateDistTo(to.getCoordinate())>500 && wayChoice==3){
                System.out.println("choose another sending way:");
                System.out.println("distance is higher than 500km so road can not be chosen as sending way!");
                continue;
            }
            break;
        }
        System.out.println("sending date&time:");
        sendingDate = Date.createDate();
        while (true){
            System.out.println("receiving date&time:");
            receivingDate = Date.createDate();
            if (sendingDate.isEarlierThan(receivingDate)){
                break;
            }else {
                System.out.println("receiving date should be after sending date!");
            }
        }
        Parcel parcel = new Parcel(name,sender,receiver,from,to,current,weight,sendingDate,receivingDate,sendingWay,postType,PostStatue.NOT_SENT);
        parcels.add(parcel);
        parcel.showCost();

    }

    static void showParcels(List<Parcel> parcels) {
        for (int i = 1; i <= parcels.size(); i++) {
            System.out.println(i+"."+parcels.get(i-1));
        }
    }

    static void trackParcel(List<Parcel> parcels) {
        System.out.println("choose a parcel:");
        showParcels(parcels);
        System.out.println("statue: "+parcels.get(scanRightChoice(parcels.size())-1).getPostStatue());
    }

    static void sendParcels(List<Parcel> parcels) {
        System.out.println("Enter the date:");
        Date sendDate = Date.createDate();
        for (Parcel parcel : parcels){
            if (parcel.getPostStatue()==PostStatue.NOT_SENT && parcel.getSendingDate().isEarlierThan(sendDate)){
                parcel.setPostStatue(PostStatue.NOT_RECEIVED);
            }
        }
    }

    static void updateParcels(List<Parcel> parcels, List<City> cities) {
        System.out.println("choose a parcel:");
        showParcels(parcels);
        Parcel parcel = parcels.get(scanRightChoice(parcels.size())-1);
        parcel.update(cities);
        parcel.update(cities);
    }

    static void searchParcel(List<Customer> customers, List<City> cities) {
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
