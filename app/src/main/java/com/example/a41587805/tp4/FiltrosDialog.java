package com.example.a41587805.tp4;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.a41587805.tp4.model.Ciudad;

import java.util.ArrayList;

/**
 * Created by 41587805 on 30/8/2016.
 */
public class FiltrosDialog extends DialogFragment implements View.OnClickListener {

    Button aceptar;
    EditText habitantes;
    CheckBox capitales;
    MainActivity ma;
    ArrayList<Ciudad> ciudades;
    int cantHabitantes;
    boolean capital;

    public FiltrosDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filtros, container);

        getDialog().setTitle("Seleccione los filtros");
        aceptar= (Button) view.findViewById(R.id.aceptar);
        habitantes = (EditText)view.findViewById(R.id.habitantes);
        capitales = (CheckBox) view.findViewById(R.id.capitales);
        aceptar.setOnClickListener(this);

        cantHabitantes = 0;
        ciudades = new ArrayList<>();

        ma= (MainActivity) getActivity();

        return view;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.aceptar:
                if(!habitantes.getText().toString().isEmpty()){
                    cantHabitantes = Integer.valueOf(habitantes.getText().toString());
                }
                if (capitales.isChecked()){
                    capital = true;
                } else { capital = false; }
                for (Ciudad c : ma.getCiudades()){
                    if (c.getPoblacion() > cantHabitantes && c.getClase().equals("PPLC")){
                        ciudades.add(c);
                    }
                }
                ma.setCiudades(ciudades);
                break;
        }
        dismiss();
    }


}
