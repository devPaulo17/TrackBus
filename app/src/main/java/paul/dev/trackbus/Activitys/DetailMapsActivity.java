package paul.dev.trackbus.Activitys;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import paul.dev.trackbus.Interfaces.RequestIF;
import paul.dev.trackbus.Models.Bus;
import paul.dev.trackbus.Models.CoordinatesBus;
import paul.dev.trackbus.R;
import paul.dev.trackbus.Utils.JSONResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailMapsActivity extends AppCompatActivity  implements
        OnMapReadyCallback{

    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int POLYLINE_STROKE_WIDTH_PX = 4;

    private String name;
    private String description;
    private String stop_url;
    private String idBus;


    private TextView txvOrigen;
    private TextView txvEstimedTime;




    ArrayList<LatLng> coordList;
    JSONArray array = null;

    private Marker start;
    private Marker stateCurrent;


    private GoogleMap mMap;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_maps);

        txvOrigen = (TextView) findViewById(R.id.origen);
        txvEstimedTime = (TextView) findViewById(R.id.estimed_time);


        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.close);


        name = getIntent().getStringExtra("name");
        description = getIntent().getStringExtra("description");
        stop_url = getIntent().getStringExtra("stops_url");
        txvOrigen.setText(description);

        idBus = cutURL(stop_url);





        Window window = getWindow();
        Explode t0 = new Explode();
        window.setEnterTransition(t0);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if(status == ConnectionResult.SUCCESS) {

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }else{
            Dialog dialog =  GooglePlayServicesUtil.getErrorDialog(status,(Activity)getApplicationContext(),10);
            dialog.show();
        }
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        coordList = new ArrayList<LatLng>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/bins/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RequestIF request = retrofit.create(RequestIF.class);
        Call<Bus> call = request.getDetailBus(idBus);
        call.enqueue(new Callback<Bus>() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(Call<Bus> call, Response<Bus> response) {

                Bus jsonResponse = response.body();
                 HashMap<String, String> stats = null;


                long minutes = TimeUnit.MILLISECONDS.toMinutes((long) jsonResponse.getEstimated_time_milliseconds());

                txvEstimedTime.setText(minutes + " minutos");

                for (int i = 0; i < jsonResponse.getStops().size(); i++) {


                    coordList.add(new LatLng(jsonResponse.getStops().get(i).getLat(),jsonResponse.getStops().get(i).getLng()));

                }




                // Add a marker in Sydney and move the camera
                Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .addAll(coordList));



                LatLng startBus =  coordList.get(0);
                LatLng current =  coordList.get(9);

                // Position the map's camera near Alice Springs in the center of Australia,
                // and set the zoom factor so most of Australia shows on the screen.
                stylePolyline(polyline1);

                start = googleMap.addMarker(new MarkerOptions().position(startBus)
                        .title(description)
                        .position(startBus)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.school)));

                start.showInfoWindow();

                stateCurrent = googleMap.addMarker(new MarkerOptions().position(current)
                        .title(name)
                        .position(current)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));

                stateCurrent.showInfoWindow();

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(start.getPosition());
                builder.include(stateCurrent.getPosition());

                LatLngBounds bounds = builder.build();

                int width = getResources().getDisplayMetrics().widthPixels;
                int height = getResources().getDisplayMetrics().heightPixels;
                int padding = (int) (width * 0.30); // offset from edges of the map 10% of screen

                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);

                googleMap.animateCamera(cu);




                // Set listeners for click events.



            }

            @Override
            public void onFailure(Call<Bus> call, Throwable t) {
                Log.w("Error",t.getMessage());
            }
        });

    }


    private void getCoordenates(ArrayList jsonResponse) throws JSONException {





    }

    private String cutURL(String url){

        String stops_url = url;
        String[] separated = stops_url.split("/");

        return separated[4];

    }

    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }




}
