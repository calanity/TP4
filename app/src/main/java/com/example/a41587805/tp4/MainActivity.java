package com.example.a41587805.tp4;


import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.a41587805.tp4.model.Ciudad;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Ciudad ciudadElegida;
    ArrayList<Ciudad> ciudadeselegidas;
    ArrayList<Ciudad> Ciudades;
    MapaFragment fragmentMapa;


    int cantidadTotal;
    int cantidadAciertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        invalidateOptionsMenu();

        ciudadeselegidas = new ArrayList<>();
        Ciudades = new ArrayList<>();

        fragmentMapa = new MapaFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenido_principal, fragmentMapa)
                .commit();

        cantidadTotal=0;
        cantidadAciertos=0;

        String url = "https://tp4ort.firebaseio.com/geonames.json";
        new BuscarDatosTask().execute(url);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(getApplicationContext()).inflate(R.menu.filtros, menu);
        return (super.onCreateOptionsMenu(menu));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_filtros:
                FragmentManager fm = getSupportFragmentManager();
                FiltrosDialog filtrosDialog = new FiltrosDialog();
                filtrosDialog.show(fm, "fragment_filtros");
                break;
        }
        return true;
    }


    public void principal(View view)
    {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.contenido_principal, fragmentMapa)
                .commit();
        fragmentMapa.cargarPreguntas();

    }

    public void resultado(View view)
    {
        Fragment resultadoFrag= new ResultadoFragment();
        FragmentManager fm2 = getSupportFragmentManager();
        fm2.beginTransaction()
                .replace(R.id.contenido_principal, resultadoFrag)
                .commit();
    }

    public ArrayList<Ciudad> getCiudadeselegidas(){
        return ciudadeselegidas;
    }

    public ArrayList<Ciudad> getCiudades(){
        return Ciudades;
    }

    public void setCiudades(ArrayList<Ciudad> ciudades){
        fragmentMapa.setCiudades(ciudades);
    }

    private class BuscarDatosTask extends AsyncTask<String, Void, ArrayList<Ciudad>> {

        protected void onPostExecute(ArrayList<Ciudad> ciudades) {
            super.onPostExecute(ciudades);
            if (!ciudades.isEmpty()) {
                Ciudades.clear();
                Ciudades.addAll(ciudades);
                fragmentMapa.setCiudades(Ciudades);
            }
        }

        @Override
        protected ArrayList<Ciudad> doInBackground(String... parametros) {
            OkHttpClient client = new OkHttpClient();
            String url = parametros[0];
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return parsearResultado(response.body().string());      // Convierto el resultado en ArrayList<>
            } catch (IOException | JSONException e) {
                Log.d("Error", e.getMessage());                          // Error de Network o al parsear JSON
                return new ArrayList<>();
            }

        }

        ArrayList<Ciudad> parsearResultado(String result) throws JSONException {
            ArrayList<Ciudad> ciudades = new ArrayList<>();
            JSONArray jsonCiudades = new JSONArray(result);
            for (int i = 0; i < jsonCiudades.length(); i++) {
                JSONObject jsonResultado = jsonCiudades.getJSONObject(i);
                String jsonClase = jsonResultado.getString("clase");
                String jsonPais = jsonResultado.getString("countrycode");
                double jsonLatitud = jsonResultado.getDouble("lat");
                double jsonLongitud = jsonResultado.getDouble("lng");
                String jsonNombre = jsonResultado.getString("name");
                int jsonPoblacion = jsonResultado.getInt("population");
                Ciudad ciu = new Ciudad(jsonNombre, jsonPoblacion, jsonClase, jsonPais, jsonLatitud, jsonLongitud);
                ciudades.add(ciu);
        //        Log.d("Ciudad", ciu.getNombre());
            }
            return ciudades;
        }
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadAciertos() {
        return cantidadAciertos;
    }

    public void setCantidadAciertos(int cantidadAciertos) {
        this.cantidadAciertos = cantidadAciertos;
    }

}

