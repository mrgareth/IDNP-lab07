package com.example.lab07.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lab07.R;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegistroFragment extends Fragment {

    private EditText edtAutor, edtTitulo, edtTecnica, edtCategoria, edtDescripcion, edtAnio;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_WRITE_STORAGE = 112;

    private String mParam1;
    private String mParam2;

    public RegistroFragment() {}

    public static RegistroFragment newInstance(String param1, String param2) {
        RegistroFragment fragment = new RegistroFragment();
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
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        Button btnAceptar = view.findViewById(R.id.btnAceptar);
        edtAutor = view.findViewById(R.id.edtAutor);
        edtTitulo = view.findViewById(R.id.edtTitulo);
        edtTecnica = view.findViewById(R.id.edtTecnica);
        edtCategoria = view.findViewById(R.id.edtCategoria);
        edtDescripcion = view.findViewById(R.id.edtDescripción);
        edtAnio = view.findViewById(R.id.edtAnio);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToFile();
                String autor = edtAutor.getText().toString();
                String titulo = edtTitulo.getText().toString();
                String tecnica = edtTecnica.getText().toString();
                String categoria = edtCategoria.getText().toString();
                String descripcion = edtDescripcion.getText().toString();
                String anio = edtAnio.getText().toString();

                VisualizacionFragment visualizacionFragment = new VisualizacionFragment();
                Bundle args = new Bundle();
                args.putString("autor", autor);
                args.putString("titulo", titulo);
                args.putString("tecnica", tecnica);
                args.putString("categoria", categoria);
                args.putString("descripcion", descripcion);
                visualizacionFragment.setArguments(args);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, visualizacionFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        boolean hasPermission = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (!hasPermission) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
        }

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(getActivity(), "Permiso de escritura denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveDataToFile() {
        String autor = edtAutor.getText().toString();
        String titulo = edtTitulo.getText().toString();
        String tecnica = edtTecnica.getText().toString();
        String categoria = edtCategoria.getText().toString();
        String descripcion = edtDescripcion.getText().toString();
        String anio = edtAnio.getText().toString();

        String data = "Autor: " + autor + "\n" +
                "Título: " + titulo + "\n" +
                "Técnica: " + tecnica + "\n" +
                "Categoría: " + categoria + "\n" +
                "Descripción: " + descripcion + "\n" +
                "Año: " + anio;

        FileOutputStream fos = null;
        try {
            fos = getActivity().openFileOutput("datos.txt", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            Toast.makeText(getActivity(), "Datos guardados en el almacenamiento interno", Toast.LENGTH_SHORT).show();
            String filePath = getActivity().getFilesDir().getPath().toString() + "/datos.txt";
            Log.d("RegistroFragment", "Archivo guardado en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
