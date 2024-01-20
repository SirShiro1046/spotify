package com.example.spotify.interfaces;

import com.example.spotify.persistence.entities.Musica;

import java.util.List;

public interface IMusicaRepository {
    List<Musica> findAllMusicas();
}
