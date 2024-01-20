package com.example.spotify.controller;

import com.example.spotify.clases.CsvReader;
import com.example.spotify.clases.LeerFuncional;
import com.example.spotify.domain.musica.IMusicaDomainService;
import com.example.spotify.interfaces.IMusicaRepository;
import com.example.spotify.persistence.entities.Musica;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<Musica>> primerosDiez(){
        log.info("Obteniendo primeras diez canciones");
        return Optional
                .of(iMusicaDomainService.primerosDiez())
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.notFound()::build);
    }


}
