package com.example.spotify.controller;

import com.example.spotify.domain.musica.IMusicaDomainService;
import com.example.spotify.persistence.entities.Musica;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/spotify")
public class MusicaController {
    private final IMusicaDomainService iMusicaDomainService;
    // lista de todas las cancionnes
    @GetMapping
    public ResponseEntity<List<Musica>> findAllMusica(){
        log.info("Obteniendo todas las camciones");
        return Optional
                .of(iMusicaDomainService.findAllMusica())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    //Numero total de canciones
    @GetMapping("/total")
    public ResponseEntity<Integer> findNumberTotalMusica() {
        log.info("Obteniendo el total de canciones");
        return iMusicaDomainService.findNumberTotalMusica()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
    //numero total de artistas
    @GetMapping("/artista/total")
    public ResponseEntity<Integer> findNumberTotalArtist(){
        log.info("Obteniendo el total de artistas");
        return iMusicaDomainService.findNumberTotalArtist()
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    //Lista de todos los artistas
    @GetMapping("/artist")
    public ResponseEntity<List<String>> findAllArtist(){
        log.info("Obteniendo todos los artistas");
        return Optional
                .of(iMusicaDomainService.findAllArtist())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    //Buscando musicas por nombre
    @GetMapping("/musica/{music}")
    public ResponseEntity<List<Musica>> findMusicaByNombre(@PathVariable String music){
        log.info("Buscando canciones por nombre: [{}]", music);
        return Optional
                .of(iMusicaDomainService.findMusicaByName(music))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
    //lista de canciones con una popularidad superior especificada
    @GetMapping("/musica/popularity/{popularity}")
    public ResponseEntity<List<Musica>> findAllMusicaByPopularity(@PathVariable Double popularity){
        log.info("Obteniendo el total de canciones con popularidad mayor a: [{}]",popularity);
        return Optional
                .of(iMusicaDomainService.findAllMusicaByPopularity(popularity))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
    //Lista de canciones con una duracion mayor a la especificada
    @GetMapping("/musica/duracion/{duration_ms}")
    public ResponseEntity<List<Musica>> findAllMusicaByDuration_ms(@PathVariable Double duration_ms){
        log.info("Obteniendo el total de canciones con duracion mayor a: [{}]",duration_ms);
        return Optional
               .of(iMusicaDomainService.findAllMusicaByDuration_ms(duration_ms))
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }
    //Cancion con la mayor energia percibida
    @GetMapping("/musica/energy")
    public ResponseEntity<Musica> findMusicaEnergy(){
        log.info("Obteniendo la cancion con mayor energia");
        return iMusicaDomainService.findMusicaEnergy()
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }
    //Cancion mas popular
    @GetMapping("/musica/popularity")
    public ResponseEntity<Musica> findMusicaPopularity(){
        log.info("Obteniendo la cancion mas popular");
        return iMusicaDomainService.findMusicaPopularity()
             .map(ResponseEntity::ok)
             .orElseGet(ResponseEntity.notFound()::build);
    }
    //Total de genero que existen
    @GetMapping("/genero/total")
    public ResponseEntity<Integer> findNumberTotalGenre(){
        log.info("Obteniendo el total de generos");
        return iMusicaDomainService.findNumberTotalGenre()
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }

    // medias

    //Lista de genero que existen
    @GetMapping("/genero")
    public ResponseEntity<List<String>> findAllGenre(){
        log.info("Obteniendo todos los generos");
        return Optional
                .of(iMusicaDomainService.findAllGenre())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
    //lista de las 10 canciones mas populares
    @GetMapping("/musica/most10popularity")
    public ResponseEntity<List<Musica>> find10MostPopularMusica(){
        log.info("Obteniendo las 10 canciones mas populares");
        return Optional
               .of(iMusicaDomainService.find10MostPopularMusica())
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }

    //total de canciones de un artista especifico
    @GetMapping("/total/{name}")
    public ResponseEntity<Integer> findNumberTotalMusicaByArtist(@PathVariable String name) {
        log.info("Obteniendo el total de canciones por artista: [{}]", name);
        return iMusicaDomainService.findNumberTotalMusicaByArtist(name)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }
    //todas las canciones de un artista especifico
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<Musica>> findAllMusicaByArtist(@PathVariable String artist){
        log.info("Buscando todas las canciones de un artista [{}]", artist);
        return Optional
                .of(iMusicaDomainService.findAllMusicaByArtist(artist))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    // total de generos que tiene un artista espeficio
    @GetMapping("/genero/total/{artist}")
    public ResponseEntity<Integer>findTotalGenreByArtista(@PathVariable String artist){
        log.info("Obteniendo total de generos que tiene un artista");
        return iMusicaDomainService.findTotalGenreByArtista(artist)
        .map(ResponseEntity::ok)
        .orElseGet(ResponseEntity.notFound()::build);
    }

    // musica especifica de artista especifico
    @GetMapping("musica/artists/{musicaName}")
    public ResponseEntity<Musica> findMusicSpecificOfArtist(@PathVariable String musicaName){
        log.info("Buscando musica especifica de con nombre y artista [{}]",musicaName);
        return iMusicaDomainService.findMusicSpecificOfArtist(musicaName)
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }
    //lista de todos los albums
    @GetMapping("/album")
    public ResponseEntity<List<String>> findAllAlbums(){
        log.info("Obteniendo todos los albums");
        return Optional
               .of(iMusicaDomainService.findAllAlbums())
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }
    //todas las canciones que no conntienen voces
    @GetMapping("/musica/voiceoff")
    public ResponseEntity<List<Musica>> findAllMusicVoiceOff(){
        log.info("Obteniendo todas las canciones que no conntienen voces");
        return Optional
              .of(iMusicaDomainService.findAllMusicVoiceOff())
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }
    // cancion mas popular de un artista especifico
    @GetMapping("/musica/artist/popularity/{artist}")
    public ResponseEntity<Musica> findMusicPopularityByArtist(@PathVariable String artist){
        log.info("Buscando cancion mas popular de un artista [{}]", artist);
        return iMusicaDomainService.findMusicPopularityByArtist(artist)
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }
    // canciones con tempo entre unos valores
    @GetMapping("/musica/rangetempo")
    public ResponseEntity<List<Musica>> findMusicByRangeTempo(@RequestBody Double[] tempo){
        log.info("Obteniendo todas las canciones con un rango de tempo [{}]", tempo);
        return Optional
              .of(iMusicaDomainService.findAllMusicByRangeTempo(tempo))
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }

    // total de tiempo de musica por artista
    @GetMapping("/musica/total/min/{artist}")
    public ResponseEntity<Double> findTotalMinMusicaByArtist(@PathVariable String artist){
        log.info("Obteniendo el total de minutos de musica por artista [{}]", artist);
        return iMusicaDomainService.findTotalMinMusicaByArtist(artist)
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }

    // cancion mas larga de un artista especifico
    @GetMapping("/musica/artist/longest/{artist}")
    public ResponseEntity<Musica> findLongestMusicaByArtist(@PathVariable String artist){
        log.info("Buscando cancion mas larga de un artista [{}]", artist);
        return iMusicaDomainService.findLongestMusicaByArtist(artist)
             .map(ResponseEntity::ok)
             .orElseGet(ResponseEntity.notFound()::build);
    }
    //artistas con mayor total de canciones
    @GetMapping("/artist/total_music")
    public ResponseEntity<List<String>> findTheArtistWithTheMostMusic(){
        log.info("Obteniendo artistas con mayor total de canciones");
        return Optional
               .of(iMusicaDomainService.findTheArtistWithTheMostMusic())
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }

    // cancion mas popular de un genero especifico
    @GetMapping("/musica/genre/popularity/{genre}")
    public ResponseEntity<Musica> findTheMostPopularSongByBenre(@PathVariable String genre){
        log.info("Buscando cancion mas popular de un genero [{}]", genre);
        return iMusicaDomainService.findTheMostPopularSongByBenre(genre)
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }
    // canciones que no tengan voces por artista
    @GetMapping("/musica/artist/without_vocals/{artist}")
    public ResponseEntity<List<Musica>> findSongsWithoutVocalsByArtist(@PathVariable String artist){
        log.info("Buscando canciones que no tengan voces por artista [{}]", artist);
        return Optional
             .of(iMusicaDomainService.findSongsWithoutVocalsByArtist(artist))
             .map(ResponseEntity::ok)
             .orElseGet(ResponseEntity.notFound()::build);
    }

    // artistas que tengan canciones sin voces
    @GetMapping("/artist/music_vocalsOff")
    public ResponseEntity<List<String>> findArtistByMusicVocals(){
        log.info("Obteniendo artistas que tengan canciones sin voces");
        return Optional
              .of(iMusicaDomainService.findArtistByMusicVocals())
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }



}
