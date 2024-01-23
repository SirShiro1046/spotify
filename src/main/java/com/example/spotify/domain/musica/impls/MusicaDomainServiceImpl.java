package com.example.spotify.domain.musica.impls;

import com.example.spotify.clases.LeerFuncional;
import com.example.spotify.domain.musica.IMusicaDomainService;
import com.example.spotify.persistence.entities.Musica;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MusicaDomainServiceImpl implements IMusicaDomainService {
    private final LeerFuncional leerFuncional = new LeerFuncional();
    @Override
    public List<Musica> findAllMusica() {
        log.info("Obteniendo primeras diez canciones");
        return  leerFuncional.getMusicas();
    }

    @Override
    public List<Musica> findAllMusicaByArtist(String artist) {
        log.info("Buscando todas las canciones de un artista [{}]", artist);
        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getTrack_artist().equalsIgnoreCase(artist))
                .toList();
    }

    @Override
    public List<String> findAllArtist() {
        log.info("Obteniendo artistas");
        List<Musica> musicaList = leerFuncional.getMusicas();

        Map<String,Musica> musicaPorArtista = musicaList.stream()
                .collect(Collectors.toMap(Musica::getTrack_artist,
                        Function.identity(),(existing,replacement)->existing));

        List<Musica> musicasDistintas = musicaPorArtista.values().stream().toList();

        List<String> artist = new ArrayList<>();
        for (Musica musica : musicasDistintas) {
            artist.add(musica.getTrack_artist());
        }
        return artist;
    }

    @Override
    public List<Musica> findMusicaByName(String name) {
        log.info("Buscando canciones por nombre: [{}]", name);
        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getTrack_name().equalsIgnoreCase(name))
                .toList();

    }

    @Override
    public Optional<Integer> findNumberTotalMusica() {
        log.info("Obteniendo el total de canciones");
        return  Optional.of((int) leerFuncional.getMusicas()
                .stream()
                .count());
    }

    @Override
    public Optional<Integer> findNumberTotalMusicaByArtist(String name) {
        log.info("Obteniendo el total de canciones por artista: [{}]", name);
        return Optional.of((int) findAllMusicaByArtist(name)
                .stream()
                .count());
    }

    @Override
    public Optional<Integer> findNumberTotalArtist() {
        log.info("Obteniendo el total de artistas");
        return Optional.of((int) findAllArtist().stream().count());
    }

    @Override
    public List<Musica> findAllMusicaByPopularity(Double popularity) {
        log.info("Obteniendo el total de canciones con popularidad mayor a: [{}]",popularity);
        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getTrack_popularity() >= popularity)
                .toList();
    }

    @Override
    public List<Musica> findAllMusicaByDuration_ms(Double duration_ms) {
        log.info("Obteniendo el total de canciones con duracion mayor a: [{}]",duration_ms);
        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getDuration_ms() >= (duration_ms*60*1000))
                .toList();
    }

    @Override
    public Optional<Musica> findMusicaEnergy() {
        Optional<Musica> musica;
        return musica = leerFuncional.getMusicas()
                .stream()
                .sorted(Comparator.comparing(Musica::getEnergy).reversed())
                .findFirst();
    }

    @Override
    public Optional<Musica> findMusicaPopularity() {
        Optional<Musica> musica;
        return musica = leerFuncional.getMusicas()
                .stream()
                .sorted(Comparator.comparing(Musica::getTrack_popularity).reversed())
                .findFirst();
    }

    @Override
    public List<String> findAllGenre() {
        log.info("Obteniendo todos los generos");
        List<Musica> musicaList = leerFuncional.getMusicas();

        Map<String,Musica> musicaPorGenero = musicaList.stream()
                .collect(Collectors.toMap(Musica::getPlaylist_genre,
                        Function.identity(),(existing,replacement)->existing));

        List<Musica> musicasDistintas = musicaPorGenero.values().stream().toList();

        List<String> generos = new ArrayList<>();
        for (Musica musica : musicasDistintas) {
            generos.add(musica.getPlaylist_genre());
        }
        return generos;
    }

    @Override
    public Optional<Integer> findNumberTotalGenre() {
        log.info("Obteniendo el total de generos");
        return Optional.of((int) findAllGenre().stream().count());
    }

    @Override
    public List<Musica> find10MostPopularMusica() {
        return leerFuncional.getMusicas()
                .stream()
                .sorted(Comparator.comparing(Musica::getTrack_popularity).reversed()).limit(10).toList();
    }

    @Override
    public Optional<Integer> findTotalGenreByArtista(String nameArtista) {
        log.info("Buscando cantidad de generos que tiene un artista [{}]",nameArtista);
        Map<String,Musica> musicaPorGenero = findAllMusicaByArtist(nameArtista).stream()
                .collect(Collectors.toMap(Musica::getPlaylist_genre,
                        Function.identity(),(existing,replacement)->existing));

        return Optional.of((int)musicaPorGenero.values().stream().count()) ;
       
    }

}
