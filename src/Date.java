import java.util.Scanner;

public class Date {
    private int min;
    private int hour;
    private int day;
    private int month;
    private int year;

    public Date(int min, int hour, int day, int month, int year) {
        if (min>59 || min<0 || hour>23 || hour<0 || day<0 || day>31 || month<0 || month>12 ||
                (month>6 && day==31) || (month==12 && year%4==3 && day==30) || year<1399 || year>1400){
            this.min = this.hour = this.day = this.month = this.year = -5;
            System.err.println("wrong Date path.");
        }else {
            this.min = min;
            this.hour = hour;
            this.day = day;
            this.month = month;
            this.year = year;
        }
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
            return hour;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Time:["+hour+":"+min+"]-"+"Date:["+year+"/"+month+"/"+day+"]";
    }
    public boolean isEarlierThan(Date toCompare){
        return this.calculateLength() < toCompare.calculateLength();
    }
    private long calculateLength(){
        long length = this.min + this.hour*60 + this.day*24*60;
        for (int i = 1; i <= month-1; i++) {
            if (i<7){
                length+=31*24*60;
            }else if (i==12){
                if (this.year%4==3){
                    length+=29*24*60;
                }else {
                    length+=30*24*60;
                }
            }else {
                length+=30*24*60;
            }
        }
        if (year==1400){
            length+=366*24*60;
        }
        System.out.println(this+" "+length);
        return length;
    }

    static public Date createDate(){
        Scanner scanner = new Scanner(System.in);
        int hour , minute , day , month , year;
        while (true){
            System.out.println("time:(24h)\nhour:");
            hour = scanner.nextInt();
            System.out.println("minute:");
            minute = scanner.nextInt();
            if (hour<24 && hour>=0 && minute>=0 && minute<60){
                break;
            }else {
                System.out.println("wrong time path!");
            }
        }
        while (true){
            System.out.println("date:(jalali|99-00)\nday:");
            day = scanner.nextInt();
            System.out.println("month:");
            month = scanner.nextInt();
            System.out.println("year:");
            year = scanner.nextInt();
            if (day<0 || day>31 || month<0 || month>12 || (month>6 && day==31)
                    || (month==12 && year%4==3 && day==30) || year<1399 || year>1400) {
                System.out.println("wrong date path!");
            }else {
                break;
            }
        }
        return new Date(minute,hour,day,month,year);

    }

}
