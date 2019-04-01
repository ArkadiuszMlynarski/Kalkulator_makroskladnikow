package com.example.msi.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {

    ArrayList<dish> dishlist;
    int sumaBialka;
    int sumaWeglowodany;
    int sumaTluszcze;
    int sumaKcal;
    String mainbialko, mainweglowodany, maintluszcze, mainkcal;
    ProgressBar progressBarBialko, progressBarWeglowodany, progressBarTluszcze, progressBarKcal;
    EditText editTextNazwa, editTextBialko, editTextWeglowodany, editTextTluszcze,editTextKcal;
    Button buttonDodaj;
    TextView textViewBialko, textViewWeglowodany, textViewTluszcze, textViewKcal;
    BottomNavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = (new Intent(secondActivity.this, MainActivity.class));

         progressBarBialko = (ProgressBar) findViewById(R.id.progressBarBialko);
         progressBarWeglowodany = (ProgressBar) findViewById(R.id.progressBarWeglowodany);
         progressBarTluszcze = (ProgressBar) findViewById(R.id.progressBarTluszcze);
         progressBarKcal = (ProgressBar) findViewById(R.id.progressBarKcal);
         editTextNazwa = (EditText) findViewById(R.id.editTextNazwa);
         editTextBialko = (EditText) findViewById(R.id.editTextBialko);
         editTextWeglowodany = (EditText) findViewById(R.id.editTextWeglowodany);
         editTextTluszcze = (EditText) findViewById(R.id.editTextTluszcze);
         editTextKcal = (EditText) findViewById(R.id.editTextKcal);
         buttonDodaj = (Button) findViewById(R.id.buttonDodaj);
         textViewBialko = (TextView) findViewById(R.id.textViewBialko);
         textViewWeglowodany = (TextView) findViewById(R.id.textViewWeglowodany);
         textViewTluszcze = (TextView) findViewById(R.id.textViewTluszcze);
         textViewKcal = (TextView) findViewById(R.id.textViewKcal);

        //ustawienie kolorow dla progressbarow
        progressBarBialko.getProgressDrawable().setColorFilter(ContextCompat.getColor(secondActivity.this, R.color.colorAccent), PorterDuff.Mode.SRC_IN );
        progressBarWeglowodany.getProgressDrawable().setColorFilter(ContextCompat.getColor(secondActivity.this, R.color.zielony), PorterDuff.Mode.SRC_IN );
        progressBarTluszcze.getProgressDrawable().setColorFilter(ContextCompat.getColor(secondActivity.this, R.color.pomaranczowy), PorterDuff.Mode.SRC_IN );
        progressBarKcal.getProgressDrawable().setColorFilter(ContextCompat.getColor(secondActivity.this, R.color.czarny), PorterDuff.Mode.SRC_IN );



        ListView mListView = (ListView) findViewById(R.id.listView);
        loadData();
        DishListAdapter adapter = new DishListAdapter(this, R.layout.adapter_view_layout, dishlist);
        mListView.setAdapter(adapter);

        //odczyt z poprzedniego activity makroskladnikow
        if(getIntent().getStringExtra("bialko")!=null||getIntent().getStringExtra("weglowodany")!=null||getIntent().getStringExtra("tluszcze")!=null||getIntent().getStringExtra("kcal")!=null) {
            mainbialko = getIntent().getStringExtra("bialko");
            mainweglowodany = getIntent().getStringExtra("weglowodany");
            maintluszcze = getIntent().getStringExtra("tluszcze");
            mainkcal = getIntent().getStringExtra("kcal");
        }
        updateSumAndProgress();



        //Usuwanie z listy przez klikniecie
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sumaBialka-=Integer.parseInt(dishlist.get(position).getBialko());
                sumaWeglowodany-=Integer.parseInt(dishlist.get(position).getWeglowodany());
                sumaTluszcze-=Integer.parseInt(dishlist.get(position).getTluszcze());
                sumaKcal-=Integer.parseInt(dishlist.get(position).getKcal());

                updateSumAndProgress();
                dishlist.remove(position);
                adapter.notifyDataSetChanged();

            }
        });

        //dodawanie posilkow za pomoca przycisku
        buttonDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                if(editTextBialko.getText().toString().equals("")||editTextKcal.getText().toString().equals("")||editTextNazwa.getText().toString().equals("")||editTextTluszcze.getText().toString().equals("")||editTextWeglowodany.getText().toString().equals("")) {
                    Toast.makeText(secondActivity.this, "Nie wypelniono wszystkich pol!", Toast.LENGTH_LONG).show();
                }
                else{
                    String nazwa = editTextNazwa.getText().toString();
                    String bialko = editTextBialko.getText().toString();
                    String weglowodany = editTextWeglowodany.getText().toString();
                    String tluszcze = editTextTluszcze.getText().toString();
                    String kcal = editTextKcal.getText().toString();

                    dishlist.add(new dish(nazwa, bialko, weglowodany, tluszcze, kcal));
                    mListView.invalidateViews();            //refresh tabeli

                    sumaBialka += Integer.parseInt(bialko);
                    sumaWeglowodany += Integer.parseInt(weglowodany);
                    sumaTluszcze += Integer.parseInt(tluszcze);
                    sumaKcal += Integer.parseInt(kcal);


                    updateSumAndProgress();

                }

            }
        });





        nav_view = (BottomNavigationView) findViewById(R.id.NavBot);
        nav_view.getMenu().getItem(1).setChecked(true);
        nav_view.getMenu().getItem(2).setCheckable(false);
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.kalkulator){
                    startActivity(intent);
                    finish();
                }
                if(id == R.id.reset){
                    dishlist.clear();
                    mListView.invalidateViews();
                    sumaBialka=0;
                    sumaKcal=0;
                    sumaTluszcze=0;
                    sumaWeglowodany=0;
                    mainbialko="0";
                    maintluszcze="0";
                    mainkcal="0";
                    mainweglowodany="0";
                    updateSumAndProgress();
                    Toast.makeText(secondActivity.this, "Zresetowano dane", Toast.LENGTH_SHORT).show();
            }

                return true;
            }
        });


    }


    private void updateSumAndProgress(){
        progressBarKcal.setProgress(sumaKcal);
        progressBarBialko.setProgress(sumaBialka);
        progressBarWeglowodany.setProgress(sumaWeglowodany);
        progressBarTluszcze.setProgress(sumaTluszcze);

        if (mainbialko != null || mainkcal != null || maintluszcze != null || mainweglowodany != null) {
            progressBarKcal.setMax(Integer.parseInt(mainkcal));
            progressBarBialko.setMax(Integer.parseInt(mainbialko));
            progressBarWeglowodany.setMax(Integer.parseInt(mainweglowodany));
            progressBarTluszcze.setMax(Integer.parseInt(maintluszcze));
        }

        textViewKcal.setText(sumaKcal+"/"+mainkcal+" kcal");
        textViewBialko.setText("bialko: "+sumaBialka+"/"+mainbialko+ " g");
        textViewWeglowodany.setText("weglowodany: "+sumaWeglowodany+"/"+mainweglowodany+ " g");
        textViewTluszcze.setText("tluszcze: "+sumaTluszcze+"/"+maintluszcze+ " g");
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dishlist);
        editor.putString("task list", json);

        editor.putString("sumabialka", String.valueOf(sumaBialka));
        editor.putString("sumaweglowodany", String.valueOf(sumaWeglowodany));
        editor.putString("sumatluszcze", String.valueOf(sumaTluszcze));
        editor.putString("sumakcal", String.valueOf(sumaKcal));

        editor.putString("mainbialko", mainbialko);
        editor.putString("mainweglowodany", mainweglowodany);
        editor.putString("maintluszcze", maintluszcze);
        editor.putString("mainkcal", mainkcal);
        editor.apply();
    }


    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<dish>>() {}.getType();
        dishlist = gson.fromJson(json, type);
        sumaBialka = Integer.parseInt(sharedPreferences.getString("sumabialka",String.valueOf(sumaBialka)));
        sumaWeglowodany = Integer.parseInt(sharedPreferences.getString("sumaweglowodany",String.valueOf(sumaWeglowodany)));
        sumaTluszcze = Integer.parseInt(sharedPreferences.getString("sumatluszcze",String.valueOf(sumaTluszcze)));
        sumaKcal = Integer.parseInt(sharedPreferences.getString("sumakcal",String.valueOf(sumaKcal)));

        mainkcal = sharedPreferences.getString("mainkcal",mainkcal);
        mainbialko = sharedPreferences.getString("mainbialko",mainbialko);
        maintluszcze = sharedPreferences.getString("maintluszcze",maintluszcze);
        mainweglowodany = sharedPreferences.getString("mainweglowodany",mainweglowodany);

        if(dishlist == null){
            dishlist = new ArrayList<>();
        }
    }

    private void resetData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        editor.apply();
    }

    protected void onStop() {
        super.onStop();
        saveData();
    }
}

