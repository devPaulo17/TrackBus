package paul.dev.trackbus.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import paul.dev.trackbus.Activitys.DetailMapsActivity;
import paul.dev.trackbus.Models.Bus;
import paul.dev.trackbus.R;

/**
 * Created by paulotrujillo on 5/12/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private ArrayList<Bus> android;
    private ArrayList<Bus> androidFiltered;
    private Context mContext;



    public DataAdapter(Context context, ArrayList<Bus> android) {
        this.mContext = context;
        this.android = android;
        this.androidFiltered = android;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txt_title,txt_overview;
        private ImageView poster_image;
        private String name,description,stop_url;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txt_title = (TextView)itemView.findViewById(R.id.txt_title);
            txt_overview = (TextView)itemView.findViewById(R.id.txt_overview);
            poster_image = (ImageView)itemView.findViewById(R.id.image_post);

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            //Toast.makeText(v.getContext() , txtTitle.getText() , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(itemView.getContext() , DetailMapsActivity.class);
            // here pass id through intent
            intent.putExtra("name" , name);
            intent.putExtra("description" , description);
            intent.putExtra("stops_url" , stop_url);

            itemView.getContext().startActivity(intent);


        }
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bus_card, viewGroup, false);




        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.txt_title.setText(android.get(i).getName());
        viewHolder.txt_overview.setText(android.get(i).getDescripcion());
        String url = android.get(i).getImgURL();
        viewHolder.name = android.get(i).getName();
        viewHolder.description = android.get(i).getDescripcion();
        viewHolder.stop_url = android.get(i).getStopURL();


        Glide.with(mContext)
                .load(url)
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.ic_launcher_background))

                .into(viewHolder.poster_image);


    }





    @Override
    public int getItemCount() {
        return androidFiltered.size();
    }




   /* @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();



                if (charString.isEmpty()) {
                    androidFiltered = android;
                } else {
                    ArrayList<Movie> filteredList = new ArrayList<>();
                    for (Movie row : android) {


                        if (row.gettitle().toLowerCase().contains(charSequence.toString().toLowerCase()) || row.getoriginal_title().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filteredList.add(row);

                        }
                    }

                    androidFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.count = androidFiltered.size();
                filterResults.values = androidFiltered;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                androidFiltered = (ArrayList<Movie>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }*/








}

