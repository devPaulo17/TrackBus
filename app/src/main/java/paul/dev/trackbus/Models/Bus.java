package paul.dev.trackbus.Models;

/**
 * Created by paulotrujillo on 5/12/18.
 */

public class Bus {

    private int id;
    private String name;
    private String description;
    private String stops_url;
    private String img_url;
    private float lat;
    private float lng;

    public Bus(int id, String name, String description, String stops_url, String img_url,float lat, float lng){

        this.id = id;
        this.name = name;
        this.description = description;
        this.stops_url = stops_url;
        this.img_url = img_url;
        this.setLat(lat);
        this.setLng(lng);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
    }

    public String getStopURL() {
        return stops_url;
    }

    public void setStopURL(String stopURL) {
        this.stops_url = stopURL;
    }

    public String getImgURL() {
        return img_url;
    }

    public void setImgURL(String imgURL) {
        this.img_url = imgURL;
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
