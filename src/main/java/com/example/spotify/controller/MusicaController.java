package com.example.spotify.controller;

import com.example.spotify.domain.musica.IMusicaDomainService;
import com.example.spotify.persistence.entities.Musica;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        log.info("Obteniendo primeras diez canciones");
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

    // total de generos que tiene un artista

}
