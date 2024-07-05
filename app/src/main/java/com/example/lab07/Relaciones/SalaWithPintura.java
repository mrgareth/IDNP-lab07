package com.example.lab07.Relaciones;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.lab07.Entidades.Sala;
import com.example.lab07.Entidades.Pintura;

import java.util.List;

public class SalaWithPintura {
    @Embedded
    public Sala sala;
    @Relation(
            parentColumn = "salaID",
            entityColumn = "pinturaID"
    )
    public List<Pintura> pinturas;
}
