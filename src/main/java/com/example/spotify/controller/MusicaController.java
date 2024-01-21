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

    @GetMapping
    public ResponseEntity<List<Musica>> findAllMusica(){
        log.info("Obteniendo primeras diez canciones");
        return Optional
                .of(iMusicaDomainService.findAllMusica())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<Musica>> findAllMusicaByArtist(@PathVariable String artist){
        log.info("Obteniendo todas las canciones de un artista");
        return Optional
               .of(iMusicaDomainService.findAllMusicaByArtist(artist))
               .map(ResponseEntity::ok)
               .orElseGet(ResponseEntity.notFound()::build);
    }

    @GetMapping("/artist")
    public ResponseEntity<List<String>> findAllArtist(){
        log.info("Obteniendo artistas");
        return Optional
              .of(iMusicaDomainService.findAllArtist())
              .map(ResponseEntity::ok)
              .orElseGet(ResponseEntity.notFound()::build);
    }
    @GetMapping("/musica/{music}")
    public ResponseEntity<List<Musica>> findMusicaByNombre(@PathVariable String music){
        log.info("Buscando canciones por nombre: [{}]", music);
        return Optional
                .of(iMusicaDomainService.findMusicaByName(music))
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }

}
