package com.example.lab07.relDAO;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.lab07.Entidades.Sala;
import com.example.lab07.Relaciones.SalaWithPintura;

import java.util.List;

@Dao
public interface SalaPinturaDAO {
    @Query("SELECT nombreSala, titulo, nombreA, enlaceImg FROM Sala, Autor, Pintura WHERE salaIDP =  salaID")
    public LiveData<List<SalaWithPintura>> loadUserAndBookNames();

}
