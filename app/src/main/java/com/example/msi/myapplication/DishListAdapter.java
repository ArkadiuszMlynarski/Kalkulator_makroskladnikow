package com.example.msi.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DishListAdapter extends ArrayAdapter<dish> {

    private static final String TAG = "DishListAdapter";

    private Context mContext;
    int mResource;

    public DishListAdapter(Context context, int resource, ArrayList<dish> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String nazwa = getItem(position).getNazwa();
        String bialko = getItem(position).getBialko();
        String weglowodany = getItem(position).getWeglowodany();
        String tluszcze = getItem(position).getTluszcze();
        String kcal = getItem(position).getKcal();

        dish dish = new dish(nazwa,bialko,weglowodany,tluszcze,kcal);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvNazwa = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvBialko = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvWeglowodany = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvTluszcze = (TextView) convertView.findViewById(R.id.textView4);
        TextView tvKcal = (TextView) convertView.findViewById(R.id.textView5);

        tvNazwa.setText(nazwa);
        tvBialko.setText("bialko: "+bialko+" g");
        tvWeglowodany.setText("weglowodany: "+weglowodany+" g");
        tvTluszcze.setText("tluszcze: "+tluszcze+" g");
        tvKcal.setText(kcal+" kcal");

        return convertView;
    }
}
