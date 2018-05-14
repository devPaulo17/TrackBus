package paul.dev.trackbus.Models;

/**
 * Created by paulotrujillo on 5/14/18.
 */

public class CoordinatesBus {

    private float lat;
    private float lng;

    public CoordinatesBus(float lat, float lng){

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
}
