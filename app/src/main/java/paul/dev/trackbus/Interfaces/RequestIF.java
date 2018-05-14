package paul.dev.trackbus.Interfaces;

import paul.dev.trackbus.Models.Bus;
import paul.dev.trackbus.Utils.JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by paulotrujillo on 5/12/18.
 */

public interface RequestIF {

    @GET("/bins/10yg1t")
    Call<JSONResponse> getJSON();

    @GET("{id}")
    Call<Bus> getDetailBus(@Path("id") String id);



}
