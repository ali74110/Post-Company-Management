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
}
