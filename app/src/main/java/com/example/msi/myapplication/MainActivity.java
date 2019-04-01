package com.example.msi.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    EditText editTextWzrost;
    EditText editTextWaga;
    EditText editTextWiek;
    EditText editTextIloscTren;
    EditText editTextDlugosc;
    TextView wynik;
    RadioButton radioKob;
    RadioButton radioMez;
    double budowa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Intent intent = new Intent(MainActivity.this, secondActivity.class);

        Spinner s = findViewById(R.id.spinner);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                if(parent.getSelectedItem().toString().equals("ektomorfik")){
                    budowa=700;
                }
                if(parent.getSelectedItem().toString().equals("mezomorfik")){
                    budowa=500;
                }
                if(parent.getSelectedItem().toString().equals("endomorfik")){
                    budowa=300;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editTextWzrost = (EditText) findViewById(R.id.editTextWzrost);
        editTextWaga = (EditText) findViewById(R.id.editTextWaga);
        editTextWiek = (EditText) findViewById(R.id.editTextWiek);
        editTextIloscTren = (EditText) findViewById(R.id.editTextIloscTren);
        editTextDlugosc = (EditText) findViewById(R.id.editTextDlugosc);
        wynik = (TextView) findViewById(R.id.wynik);
        Button calcbut = (Button) findViewById(R.id.calcbut);
        Button savebut = (Button) findViewById(R.id.savebut);
        radioKob = (RadioButton) findViewById(R.id.radioKob);
        radioMez = (RadioButton) findViewById(R.id.radioMez);
        TextView textViewBialkoo = (TextView) findViewById(R.id.textViewBialkoo);
        TextView textViewWeglowodanyy = (TextView) findViewById(R.id.textViewWeglowodanyy);
        TextView textViewTluszczee = (TextView) findViewById(R.id.textViewTluszczee);



        calcbut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(editTextWzrost.getText().toString().equals("")||editTextWaga.getText().toString().equals("")||editTextWiek.getText().toString().equals("")||editTextIloscTren.getText().toString().equals("")||editTextDlugosc.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Nie wypelniono wszystkich pol!", Toast.LENGTH_LONG).show();
                }
                else {
                    float wzrost = Float.parseFloat(editTextWzrost.getText().toString());
                    float waga = Float.parseFloat(editTextWaga.getText().toString());
                    float wiek = Float.parseFloat(editTextWiek.getText().toString());
                    float ilosctren = Float.parseFloat(editTextIloscTren.getText().toString());
                    float dlugosc = Float.parseFloat(editTextDlugosc.getText().toString());
                    double bmr;
                    if (radioKob.isChecked()) {
                        bmr = (9.99 * waga) + (6.25 * wzrost) - (4.92 * wiek) - 161;
                    } else
                        bmr = (9.99 * waga) + (6.25 * wzrost) - (4.92 * wiek) + 5;
                    double tea = (ilosctren * dlugosc * 8) / 7 + budowa;
                    double tef = (bmr + tea) + ((bmr + tea) * 0.1);
                    String result = String.format("%.0f", tef);
                    wynik.setText(result + " kcal");


                    //https://www.budujmase.pl/diety/o-dietach/6298-podzial-makroskladnikow-krok-po-kroku.html
                    double bialko = 2 * waga;
                    double tluszcze = 0.25 * tef / 9;
                    double weglowodany = (tef - (bialko * 4 + 0.25 * tef)) / 4;
                    textViewBialkoo.setText("Bialko: " + String.format("%.0f", bialko));
                    textViewWeglowodanyy.setText("Weglowodany: " + String.format("%.0f", weglowodany));
                    textViewTluszczee.setText("Tluszcze: " + String.format("%.0f", tluszcze));

                    savebut.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            intent.putExtra("bialko", String.format("%.0f", bialko));
                            intent.putExtra("tluszcze", String.format("%.0f", tluszcze));
                            intent.putExtra("weglowodany", String.format("%.0f", weglowodany));
                            intent.putExtra("kcal", String.format("%.0f", tef));
                            Toast.makeText(MainActivity.this, "Wartosci makroskladnikow zapisane!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });



        BottomNavigationView nav_view = (BottomNavigationView) findViewById(R.id.NavBot);
        nav_view.getMenu().getItem(2).setEnabled(false);
        nav_view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.home){
                    startActivity(intent);
                    finish();
                }
                return true;
            }
        });
    }

}
