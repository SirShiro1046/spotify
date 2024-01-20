package com.example.spotify.domain.musica;

import com.example.spotify.persistence.entities.Musica;
import java.util.List;

public interface IMusicaDomainService {
    List<Musica> findAllMusica();
}
