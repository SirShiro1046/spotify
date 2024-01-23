package com.example.spotify.domain.musica;

import com.example.spotify.persistence.entities.Musica;
import java.util.List;
import java.util.Optional;

public interface IMusicaDomainService {
    List<Musica> findAllMusica();
    List<Musica> findAllMusicaByArtist(String artist);
    List<String> findAllArtist();
    List<Musica> findMusicaByName(String music);
    Optional<Integer> findNumberTotalMusica();
    Optional<Integer> findNumberTotalMusicaByArtist(String name);
    Optional<Integer> findNumberTotalArtist();
    List<Musica> findAllMusicaByPopularity(Double popularity);
    List<Musica> findAllMusicaByDuration_ms(Double duration_ms);
    Optional<Musica> findMusicaEnergy();
    Optional<Musica> findMusicaPopularity();
    List<String> findAllGenre();
    Optional<Integer> findNumberTotalGenre();
    List<Musica> find10MostPopularMusica();
    Optional<Integer> findTotalGenreByArtista(String nameArtista);
}
