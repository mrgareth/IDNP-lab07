package com.example.lab07.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.lab07.R;

public class VisualizacionFragment extends Fragment {

    private TextView txtNombreObra, txtAutor, txtDescripcion, txtTecnica, txtCategoria;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public VisualizacionFragment() {}

    public static VisualizacionFragment newInstance(String param1, String param2) {
        VisualizacionFragment fragment = new VisualizacionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizacion, container, false);

        txtNombreObra = view.findViewById(R.id.txtNombreObra);
        txtAutor = view.findViewById(R.id.txtAutor);
        txtDescripcion = view.findViewById(R.id.txtDescripcion);
        txtTecnica = view.findViewById(R.id.txtTecnica);
        txtCategoria = view.findViewById(R.id.txtCategoria);

        Bundle args = getArguments();
        if (args != null) {
            txtNombreObra.setText(args.getString("titulo"));
            txtAutor.setText(args.getString("autor"));
            txtDescripcion.setText(args.getString("descripcion"));
            txtTecnica.setText(args.getString("tecnica"));
            txtCategoria.setText(args.getString("categoria"));
        }

        return view;
    }
}
