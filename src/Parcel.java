import java.util.List;

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
            Main.showCities(cities);
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
}
