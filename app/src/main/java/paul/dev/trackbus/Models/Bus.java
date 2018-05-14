package paul.dev.trackbus.Models;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulotrujillo on 5/12/18.
 */

public class Bus {

    private int id;
    private String name;
    private String description;
    private String stops_url;
    private String img_url;
    private List<CoordinatesBus> stops = new ArrayList<>();
    private float lat;
    private float lng;
    private float estimated_time_milliseconds;
    private float retry_time_milliseconds;

    public Bus(int id, String name, String description, String stops_url, String img_url,int estimated_time_milliseconds,int retry_time_milliseconds){

        this.id = id;
        this.name = name;
        this.description = description;
        this.stops_url = stops_url;
        this.img_url = img_url;
        this.estimated_time_milliseconds = estimated_time_milliseconds;
        this.retry_time_milliseconds = retry_time_milliseconds;


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

    public float getEstimated_time_milliseconds() {
        return estimated_time_milliseconds;
    }

    public void setEstimated_time_milliseconds(float estimated_time_milliseconds) {
        this.estimated_time_milliseconds = estimated_time_milliseconds;
    }

    public float getRetry_time_milliseconds() {
        return retry_time_milliseconds;
    }

    public void setRetry_time_milliseconds(float retry_time_milliseconds) {
        this.retry_time_milliseconds = retry_time_milliseconds;
    }


    public List<CoordinatesBus> getStops() {
        return stops;
    }
}
