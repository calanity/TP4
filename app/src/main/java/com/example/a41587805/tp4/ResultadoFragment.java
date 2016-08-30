package com.example.a41587805.tp4;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by 41587805 on 23/8/2016.
 */
public class ResultadoFragment extends Fragment {
    public ResultadoFragment() {
    }
    TextView total, aciertos;
    MainActivity ma;
    ImageButton eliminar;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_resultado, container, false);
        ma = (MainActivity) getActivity();
        eliminar=(ImageButton)view.findViewById(R.id.eliminar);
        total= (TextView)view.findViewById(R.id.totalPreguntas);
        aciertos= (TextView)view.findViewById(R.id.totalRespuestas);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma.setCantidadAciertos(0);
                ma.setCantidadTotal(0);
                total.setText("Total preguntas realizadas: " + String.valueOf(ma.getCantidadTotal()));
                aciertos.setText("Total respuestas correctas: " +String.valueOf(ma.getCantidadAciertos()));
            }

        });


        total.setText("Total preguntas realizadas: " + String.valueOf(ma.getCantidadTotal()));
        aciertos.setText("Total respuestas correctas: " +String.valueOf(ma.getCantidadAciertos()));

        return view;
    }
}
