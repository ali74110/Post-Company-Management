import java.util.List;
import java.util.Scanner;

public class City {
    private String name;
    private Coordinate coordinate;

    public City(String name , double latitude , double longitude){
        this.name = name;
        this.coordinate = new Coordinate(latitude,longitude);
    }

    public String getName() {
        return name;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinate(double latitude,double longitude) {
        this.coordinate.setLatitude(latitude);
        this.coordinate.setLongitude(longitude);
    }

    @Override
    public String toString() {
        return  name + ": " + coordinate;
    }

    void edit(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("which part do you want to edit:");
        System.out.println("1.name:");
        System.out.println("2.coordinate");
        System.out.println("3.back");
        int editChoice = Main.scanRightChoice(3);
        if (editChoice==1){
            double latitude,longitude;
            System.out.println("new latitude:");
            latitude = scanner.nextDouble();
            System.out.println("new longitude:");
            longitude = scanner.nextDouble();
            this.setCoordinate(latitude,longitude);
        }else if (editChoice==2){
            String name;
            System.out.println("new name:");
            name = scanner.next();
            this.setName(name);
        }
    }
    static void citiesMenu( List<City> cities) {
        int choice;
        boolean end = false;
        while (!end){
            System.out.println("<< Cities Menu >>");
            System.out.println("1.add city");
            System.out.println("2.edit city");
            System.out.println("3.show cities");
            System.out.println("4.Back");
            choice = Main.scanRightChoice(4);
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
        int cityChoice = Main.scanRightChoice(cities.size());
        City city = cities.get(cityChoice-1);
        System.out.println(city.getName()+" selected.");

    }

    static void showCities(List<City> cities) {
        for (int i = 1; i <= cities.size(); i++) {
            System.out.println(i+"."+cities.get(i-1));
        }
    }
}
