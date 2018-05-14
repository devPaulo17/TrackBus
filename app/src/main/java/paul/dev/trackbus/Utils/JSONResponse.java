package paul.dev.trackbus.Utils;

import paul.dev.trackbus.Models.Bus;

/**
 * Created by paulotrujillo on 5/12/18.
 */

public class JSONResponse {

    private Bus[] school_buses;
    private Bus[] stops;



    public Bus[] getAndroid() {
        return school_buses;
    }
    public Bus[] getDetailBuses() {
        return stops;
    }
}
