package paul.dev.trackbus.Fragments;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import paul.dev.trackbus.Adapters.DataAdapter;
import paul.dev.trackbus.Interfaces.RequestIF;
import paul.dev.trackbus.Models.Bus;
import paul.dev.trackbus.R;
import paul.dev.trackbus.Utils.Alerts;
import paul.dev.trackbus.Utils.CheckConexion;
import paul.dev.trackbus.Utils.JSONResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<Bus> data;
    private DataAdapter adapter;
    SearchView searchView;
    CheckConexion connected;
    Alerts alert;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);

        alert = new Alerts(getContext());
        initViews(root);


        // Carga de datos
        if((connected.isOnline(getContext()) == true))
            listTrackBus();

        return root;

    }

    private void initViews(View root){

        recyclerView = (RecyclerView) root.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new SlideInLeftAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }

    private void listTrackBus() {

        alert.showAlert("Cargando Buses","Por favor espere...");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestIF request = retrofit.create(RequestIF.class);
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getAndroid()));
                adapter = new DataAdapter(getActivity(),data);
                ScaleInAnimationAdapter alphaAdapter = new ScaleInAnimationAdapter(adapter);
                recyclerView.setAdapter(new ScaleInAnimationAdapter(alphaAdapter));


                alert.close();

            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.w("Error",t.getMessage());
            }
        });


    }
}
