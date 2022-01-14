package sun.com.didi.entity;

public class Coordinate {

    private double longitude;  //经度
    private double latitude;   //纬度
    private String companyName;

    public Coordinate() {
    }

    public Coordinate(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coordinate(double longitude, double latitude, String companyName) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.companyName = companyName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
