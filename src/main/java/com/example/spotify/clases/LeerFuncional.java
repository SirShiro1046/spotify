package com.example.spotify.clases;

import com.example.spotify.interfaces.IMusicaRepository;
import com.example.spotify.persistence.entities.Musica;
import lombok.Getter;
import java.util.List;
public class LeerFuncional {
    IMusicaRepository csvReader = new CsvReader("src/main/java/com/example/spotify/archivos/spotify_songs.csv");
    private final IMusicaRepository musicaRepository = csvReader;
    @Getter
    List<Musica> musicas = musicaRepository.findAllMusicas();
    public LeerFuncional(){
    }

}
