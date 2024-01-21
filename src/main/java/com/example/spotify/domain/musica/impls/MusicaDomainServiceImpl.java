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

}
