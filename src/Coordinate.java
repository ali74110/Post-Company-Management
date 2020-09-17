public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double calculateDistTo(Coordinate c2){
        double rad = Math.PI / 180;
        int radius = 6371; //earth radius in kilometers
        return Math.acos(Math.sin(c2.latitude * rad) * Math.sin(this.latitude * rad) +
                Math.cos(c2.latitude * rad) * Math.cos(this.latitude * rad) * Math.cos(c2.longitude * rad - this.longitude * rad)) * radius; //result in Kilometers
    }

    @Override
    public String toString() {
        return "{" + latitude +
                "," + longitude +
                '}';
    }
}
