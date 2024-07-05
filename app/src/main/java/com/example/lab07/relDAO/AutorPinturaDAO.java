package com.example.lab07.relDAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.lab07.Relaciones.AutorWithPintura;

import java.util.List;

@Dao
public interface AutorPinturaDAO {
    @Query("SELECT * from Pintura, Autor where autorIDP = autorID ")
    public LiveData<List<AutorWithPintura>> loadUserAndBookNames();


}


