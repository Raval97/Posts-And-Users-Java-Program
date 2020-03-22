package Userparameters.AddressParameters;

public class Geo {
    private  float lat;
    private float lng;

    public Geo(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "\nlat=" + lat +
                ", \nlng=" + lng +
                "\n}";
    }

}
