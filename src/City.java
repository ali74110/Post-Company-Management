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
}
