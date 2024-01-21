package com.example.spotify.domain.musica;

import com.example.spotify.persistence.entities.Musica;
import java.util.List;
import java.util.Optional;

public interface IMusicaDomainService {
    List<Musica> findAllMusica();
    List<Musica> findAllMusicaByArtist(String artist);
    List<String> findAllArtist();
    List<Musica >findMusicaByName(String music);
}
