import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parcel {
    private String name;
    private Customer sender;
    private Customer receiver;
    private City from;
    private City to;
    private City currentCity;
    private int weight;
    private Date sendingDate;
    private Date receivingDate;
    private SendingWay sendingWay;
    private PostType postType;
    private PostStatue postStatue;

    public Parcel(String name, Customer sender, Customer receiver, City from, City to, City currentCity, int weight,
                  Date sendingDate, Date receivingDate, SendingWay sendingWay, PostType postType, PostStatue postStatue) {
        this.name = name;
        this.sender = sender;
        this.receiver = receiver;
        this.from = from;
        this.to = to;
        this.currentCity = currentCity;
        this.weight = weight;
        this.sendingDate = sendingDate;
        this.receivingDate = receivingDate;
        this.sendingWay = sendingWay;
        this.postType = postType;
        this.postStatue = postStatue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public SendingWay getSendingWay() {
        return sendingWay;
    }

    public void setSendingWay(SendingWay sendingWay) {
        this.sendingWay = sendingWay;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public PostStatue getPostStatue() {
        return postStatue;
    }

    public void setPostStatue(PostStatue postStatue) {
        this.postStatue = postStatue;
    }

    @Override
    public String toString() {
        return "Parcel " + name +
                ": sender=" + sender +
                ", receiver=" + receiver +
                ", from=" + from +
                ", to=" + to +
                ", currentCity=" + currentCity +
                ", weight=" + weight +
                ", sendingDate=" + sendingDate +
                ", receivingDate=" + receivingDate +
                ", sendingWay=" + sendingWay +
                ",post statue=" + postStatue+
                ",post type=" + postType;
    }

    public void showCost() {
        int cost = 0;
        if (sendingWay==SendingWay.AIRWAY){
            cost = (int) (2000*weight + 200*from.getCoordinate().calculateDistTo(to.getCoordinate()));
        }else if (sendingWay==SendingWay.ROAD){
            cost = (int) (1000*weight + 100*from.getCoordinate().calculateDistTo(to.getCoordinate()));
        }else {
            cost = (int) (1500*weight + 150*from.getCoordinate().calculateDistTo(to.getCoordinate()));
        }
        if (postType==PostType.SPECIAL){
            cost*=2;
        }
        System.out.println("parcel created with the cost of "+cost+"$");
    }

    public void update(List<City> cities) {
        while (true){
            System.out.println("chosen parcel: from "+this.from+" to "+this.to+",current city: "+this.currentCity);
            System.out.println("choose new current city:");
            City.showCities(cities);
            City newCurrentCity = cities.get(Main.scanRightChoice(cities.size())-1);
            if (this.from!=this.currentCity && newCurrentCity==this.from){
                System.out.println("you chose beginning city! try another.");
                continue;
            }
            if (newCurrentCity==this.to){
                this.postStatue=PostStatue.RECEIVED;
            }
            this.currentCity = newCurrentCity;
            break;
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
            choice = Main.scanRightChoice(7);
            switch (choice){
                case 1 : addParcel(customers,parcels,cities); break;
                case 2 : showParcelsByFilter(customers,parcels,cities); break;
                case 3 : trackParcel(parcels); break;
                case 4 : sendParcels(parcels); break;
                case 5 : updateParcels(parcels,cities); break;
                case 6 : searchParcel(parcels); break;
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
        int postTypeChoice = Main.scanRightChoice(2);
        if (postTypeChoice == 1) {
            postType = PostType.NORMAL;
        } else {
            postType = PostType.SPECIAL;
        }

        System.out.println("weight:");
        weight = scanner.nextInt();

        System.out.println("choose sender:");
        Customer.showCustomers(customers);
        sender = customers.get(Main.scanRightChoice(customers.size())-1);

        while (true){
            System.out.println("choose receiver:");
            Customer.showCustomers(customers);
            receiver = customers.get(Main.scanRightChoice(customers.size())-1);
            if (!sender.equals(receiver)){
                break;
            }else {
                System.out.println("sender chosen!");
            }
        }

        System.out.println("choose first city:");
        City.showCities(cities);
        from = current = cities.get(Main.scanRightChoice(cities.size())-1);

        while (true){
            System.out.println("choose destination city:");
            City.showCities(cities);
            to = cities.get(Main.scanRightChoice(cities.size())-1);
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
            int wayChoice = Main.scanRightChoice(3);
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

    static void showParcelsByFilter(List<Customer> customers , List<Parcel> parcels , List<City> cities) {
        System.out.println("1.show all parcels");
        System.out.println("2.show by sending way");
        System.out.println("3.show by parcel statue");
        System.out.println("4.show by customers");
        int choice = Main.scanRightChoice(4);
        if (choice == 1){
            showParcels(parcels);
            Main.goBack();
        }else if (choice == 2){
            System.out.println("1.Airway");
            System.out.println("2.Seaway");
            System.out.println("3.Road");
            int wayChoice = Main.scanRightChoice(3);
            List<Parcel> wantedParcels = new ArrayList<>();
            for (Parcel parcel : parcels){
                if (wayChoice==1 && parcel.getSendingWay()==SendingWay.AIRWAY){
                    wantedParcels.add(parcel);
                }else if (wayChoice==2 && parcel.getSendingWay()==SendingWay.SEAWAY){
                    wantedParcels.add(parcel);
                }else if (wayChoice==3 && parcel.getSendingWay()==SendingWay.ROAD){
                    wantedParcels.add(parcel);
                }
            }
            showParcels(wantedParcels);
            Main.goBack();
        }else if (choice == 3){
            System.out.println("1.not sent");
            System.out.println("2.sent");
            System.out.println("3.received");
            int statueChoice = Main.scanRightChoice(3);
            List<Parcel> wantedParcels = new ArrayList<>();
            for (Parcel parcel : parcels){
                if (statueChoice==1 && parcel.getPostStatue()==PostStatue.NOT_SENT){
                    wantedParcels.add(parcel);
                }else if (statueChoice==2 && parcel.getPostStatue()==PostStatue.NOT_RECEIVED){
                    wantedParcels.add(parcel);
                }else if (statueChoice==3 && parcel.getPostStatue()==PostStatue.RECEIVED){
                    wantedParcels.add(parcel);
                }
            }
            showParcels(wantedParcels);
            Main.goBack();
        }else if (choice == 4){
            System.out.println("choose a customer:");
            Customer.showCustomers(customers);
            Customer customer = customers.get(Main.scanRightChoice(customers.size()));
            List<Parcel> wantedParcels = new ArrayList<>();
            for (Parcel parcel : parcels){
                if (parcel.getSender().equals(customer) || parcel.getReceiver().equals(customer)){
                    wantedParcels.add(parcel);
                }
            }
            showParcels(wantedParcels);
            Main.goBack();
        }
    }

    static void showParcels(List<Parcel> parcels) {
        if (parcels.size()==0){
            System.out.println("no parcels exist");
            return;
        }
        for (int i = 1; i <= parcels.size(); i++) {
            System.out.println(i+"."+parcels.get(i-1));
        }
    }

    static void trackParcel(List<Parcel> parcels) {
        System.out.println("choose a parcel:");
        showParcels(parcels);
        System.out.println("statue: "+parcels.get(Main.scanRightChoice(parcels.size())-1).getPostStatue());
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
        Parcel parcel = parcels.get(Main.scanRightChoice(parcels.size())-1);
        parcel.update(cities);
        parcel.update(cities);
    }

    static void searchParcel(List<Parcel> parcels) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter parcel name:");
        String searchName = scanner.next();
        List<Parcel> wantedParcels = new ArrayList<>();
        for (Parcel parcel : parcels){
            if (parcel.getName().contains(searchName)){
                wantedParcels.add(parcel);
            }
        }
        showParcels(wantedParcels);
        Main.goBack();
    }

}
