package com.example.a41587805.tp4;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a41587805.tp4.model.Ciudad;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 41587805 on 2/8/2016.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    Button opcion1, opcion2, opcion3, opcion4;
    ImageButton siguiente;
    MainActivity ma;
    Ciudad ciudadelegida;
    ArrayList<Ciudad> ciudades;
    Toast mensaje;
    ArrayList<Ciudad> ciudadeselegidas;
    int aciertos=0;
    int cant=0;

    public MapaFragment() {
    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState)
    {
        ma = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.fragment_mapa, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        opcion1 = (Button) view.findViewById(R.id.boton0);
        opcion2 = (Button) view.findViewById(R.id.boton1);
        opcion3 = (Button) view.findViewById(R.id.boton2);
        opcion4 = (Button) view.findViewById(R.id.boton3);
        siguiente=(ImageButton)view.findViewById(R.id.siguiente);


        ciudadeselegidas = new ArrayList<>();

       opcion1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               if (opcion1.getText().equals(ciudadelegida.getNombre()))
               {
                   opcion1.setBackgroundColor(Color.GREEN);

                  // mensaje = Toast.makeText(getContext(), "Correcto", Toast.LENGTH_SHORT);
                   aciertos= ma.getCantidadAciertos();
                   aciertos++;
                   ma.setCantidadAciertos(aciertos);

                   cant =ma.getCantidadTotal();
                   cant++;
                   ma.setCantidadTotal(cant);

               }
               else {
                       opcion1.setBackgroundColor(Color.RED);
                        if (opcion2.getText().equals(ciudadelegida.getNombre()))
                        {
                            opcion2.setBackgroundColor(Color.GREEN);
                        }
                       if (opcion3.getText().equals(ciudadelegida.getNombre()))
                       {
                           opcion3.setBackgroundColor(Color.GREEN);
                       }
                       if (opcion4.getText().equals((ciudadelegida.getNombre())))
                       {
                           opcion4.setBackgroundColor(Color.GREEN);
                       }

                   //mensaje = Toast.makeText(getContext(), "Incorrecto", Toast.LENGTH_SHORT);

                   cant =ma.getCantidadTotal();
                   cant++;
                   ma.setCantidadTotal(cant);

               }

               opcion1.setEnabled(false);
               opcion2.setEnabled(false);
               opcion3.setEnabled(false);
               opcion4.setEnabled(false);
               siguiente.setVisibility(View.VISIBLE);

           }
       });

        opcion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (opcion2.getText().equals(ciudadelegida.getNombre()))
                {
                    opcion2.setBackgroundColor(Color.GREEN);
                    //mensaje = Toast.makeText(getContext(), "Correcto", Toast.LENGTH_SHORT);
                    aciertos= ma.getCantidadAciertos();
                    aciertos++;
                    ma.setCantidadAciertos(aciertos);

                    cant =ma.getCantidadTotal();
                    cant++;
                    ma.setCantidadTotal(cant);
                } else {

                    opcion2.setBackgroundColor(Color.RED);

                    if (opcion1.getText().equals(ciudadelegida.getNombre()))
                    {
                        opcion1.setBackgroundColor(Color.GREEN);
                    }
                    if (opcion3.getText().equals(ciudadelegida.getNombre()))
                    {
                        opcion3.setBackgroundColor(Color.GREEN);
                    }
                    if (opcion4.getText().equals((ciudadelegida.getNombre())))
                    {
                        opcion4.setBackgroundColor(Color.GREEN);
                    }

                   // mensaje = Toast.makeText(getContext(), "Incorrecto", Toast.LENGTH_SHORT);
                    cant =ma.getCantidadTotal();
                    cant++;
                    ma.setCantidadTotal(cant);
                }
                opcion1.setEnabled(false);
                opcion2.setEnabled(false);
                opcion3.setEnabled(false);
                opcion4.setEnabled(false);
                siguiente.setVisibility(View.VISIBLE);
            }

        });

        opcion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (opcion3.getText().equals(ciudadelegida.getNombre()))
                {
                    //mensaje = Toast.makeText(getContext(), "Correcto", Toast.LENGTH_SHORT);
                    opcion3.setBackgroundColor(Color.GREEN);

                    aciertos= ma.getCantidadAciertos();
                    aciertos++;
                    ma.setCantidadAciertos(aciertos);
                    cant =ma.getCantidadTotal();
                    cant++;
                    ma.setCantidadTotal(cant);

                } else {
                    //mensaje = Toast.makeText(getContext(), "Incorrecto", Toast.LENGTH_SHORT);
                    opcion3.setBackgroundColor(Color.RED);

                    if (opcion2.getText().equals(ciudadelegida.getNombre()))
                    {
                        opcion2.setBackgroundColor(Color.GREEN);
                    }
                    if (opcion1.getText().equals(ciudadelegida.getNombre()))
                    {
                        opcion1.setBackgroundColor(Color.GREEN);
                    }
                    if (opcion4.getText().equals((ciudadelegida.getNombre())))
                    {
                        opcion4.setBackgroundColor(Color.GREEN);
                    }
                    //buscar la correcta y marcarla
                    cant =ma.getCantidadTotal();
                    cant++;
                    ma.setCantidadTotal(cant);
                }

                opcion1.setEnabled(false);
                opcion2.setEnabled(false);
                opcion3.setEnabled(false);
                opcion4.setEnabled(false);
                siguiente.setVisibility(View.VISIBLE);

            }
        });

        opcion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (opcion4.getText().equals(ciudadelegida.getNombre()))
                {
                    //mensaje = Toast.makeText(getContext(), "Correcto", Toast.LENGTH_SHORT);
                    opcion4.setBackgroundColor(Color.GREEN);
                    cant =ma.getCantidadTotal();
                    cant++;
                    ma.setCantidadTotal(cant);

                    aciertos= ma.getCantidadAciertos();
                    aciertos++;
                    ma.setCantidadAciertos(aciertos);
                } else {
                    //mensaje = Toast.makeText(getContext(), "Incorrecto", Toast.LENGTH_SHORT);
                    opcion4.setBackgroundColor(Color.RED);
                    if (opcion2.getText().equals(ciudadelegida.getNombre()))
                    {
                        opcion2.setBackgroundColor(Color.GREEN);
                    }
                    if (opcion1.getText().equals(ciudadelegida.getNombre()))
                    {
                        opcion1.setBackgroundColor(Color.GREEN);
                    }
                    if (opcion3.getText().equals((ciudadelegida.getNombre())))
                    {
                        opcion3.setBackgroundColor(Color.GREEN);
                    }


                    cant =ma.getCantidadTotal();
                    cant++;
                    ma.setCantidadTotal(cant);
                }

                opcion1.setEnabled(false);
                opcion2.setEnabled(false);
                opcion3.setEnabled(false);
                opcion4.setEnabled(false);
                siguiente.setVisibility(View.VISIBLE);

            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                setCiudades(ciudades);

                opcion1.setBackgroundResource(android.R.drawable.btn_default_small);
                opcion2.setBackgroundResource(android.R.drawable.btn_default_small);
                opcion3.setBackgroundResource(android.R.drawable.btn_default_small);
                opcion4.setBackgroundResource(android.R.drawable.btn_default_small);

                opcion1.setEnabled(true);
                opcion2.setEnabled(true);
                opcion3.setEnabled(true);
                opcion4.setEnabled(true);
                siguiente.setVisibility(View.GONE);


            }
        });

        return view;
    }

    public void setCiudades( ArrayList<Ciudad> ciudades)
    {
        this.ciudades = ciudades;
        ciudadeselegidas= ObtenerCiudadesRandom(ciudades);
        if (ciudades != null && map != null)
            cargarPreguntas();
    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ciudades != null && map != null)
            cargarPreguntas();

    }

    public ArrayList<Ciudad>ObtenerCiudadesRandom(ArrayList<Ciudad> ciudades){
        ArrayList<Ciudad> ciudadesrandom = new ArrayList<>(ciudades);
        ciudadeselegidas.clear();
        Ciudad ciudadRandom1 = (ciudadesrandom.get(new Random().nextInt(ciudadesrandom.size())));
        Log.d("Ciudad random", ciudadRandom1.getNombre());
        ciudadesrandom.remove(ciudadRandom1);
        ciudadeselegidas.add(ciudadRandom1);
        Ciudad ciudadRandom2 = (ciudadesrandom.get(new Random().nextInt(ciudadesrandom.size())));
        Log.d("Ciudad random", ciudadRandom2.getNombre());
        ciudadesrandom.remove(ciudadRandom2);
        ciudadeselegidas.add(ciudadRandom2);
        Ciudad ciudadRandom3 = (ciudadesrandom.get(new Random().nextInt(ciudadesrandom.size())));
        Log.d("Ciudad random", ciudadRandom3.getNombre());
        ciudadesrandom.remove(ciudadRandom3);
        ciudadeselegidas.add(ciudadRandom3);
        Ciudad ciudadRandom4 = (ciudadesrandom.get(new Random().nextInt(ciudadesrandom.size())));
        Log.d("Ciudad random", ciudadRandom4.getNombre());
        ciudadesrandom.remove(ciudadRandom4);
        ciudadeselegidas.add(ciudadRandom4);
        return ciudadeselegidas;
    }

    public Ciudad getCiudadElegida(ArrayList<Ciudad> c){
       return (c.get(new Random().nextInt(c.size())));

    }

    public void cargarPreguntas(){


        ciudadeselegidas= ObtenerCiudadesRandom(ciudades);
        ciudadelegida = getCiudadElegida(ciudadeselegidas);

        opcion1.setText(ciudadeselegidas.get(0).getNombre());
        opcion2.setText(ciudadeselegidas.get(1).getNombre());
        opcion3.setText(ciudadeselegidas.get(2).getNombre());
        opcion4.setText(ciudadeselegidas.get(3).getNombre());

        map.clear();

        map.getUiSettings().setZoomControlsEnabled(true);// Habilita +/- para hacer zoom
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);    // Selecciona tipo de mapa satelital

        LatLng latLng = new LatLng(ciudadelegida.getLatitud(),ciudadelegida.getLongitud());
        CameraUpdate bsas= CameraUpdateFactory.newLatLng(latLng);
        map.moveCamera(bsas);
        CameraUpdate zoom=CameraUpdateFactory.zoomTo(2);
        map.animateCamera(zoom);

        MarkerOptions mo = new MarkerOptions()
                .position(latLng);
        map.addMarker(mo);

    }

}
