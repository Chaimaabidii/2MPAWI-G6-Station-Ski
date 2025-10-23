package com.example.gestionstationskii.services;

import com.example.gestionstationskii.entities.Piste;
import java.util.List;

public interface IPisteServices {

    Piste addPiste(Piste piste);

    List<Piste> retrieveAllPistes();

    Piste retrievePiste(Long numPiste);

    void removePiste(Long numPiste);
}
