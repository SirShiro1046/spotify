package com.example.spotify.clases;

import com.example.spotify.interfaces.IMusicaRepository;
import com.example.spotify.persistence.entities.Musica;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader implements IMusicaRepository {
    private final String ruta;

    public CsvReader(String ruta) {
        this.ruta = ruta;
    }

    private Musica mapearMusica(String[] campos) {
        String track_id = campos[0];
        String track_name= campos[1];
        String track_artist=campos[2];
        Double track_popularity= (campos[3]!= "") ? Double.parseDouble(campos[3]): null;
        String track_album_id= campos[4];
        String track_album_name= campos[5];
        String track_album_release_date=campos[6];
        String playlist_name=campos[7];
        String playlist_id=campos[8];
        String playlist_genre=campos[9];
        String playlist_subgenre=campos[10];
        Double danceability=(campos[11]!="")? Double.parseDouble(campos[11]):null;
        Double energy=(campos[12]!="")? Double.parseDouble(campos[12]):null;
        Double key=(campos[13]!="")? Double.parseDouble(campos[13]):null;
        Double loudness=(campos[14]!="")? Double.parseDouble(campos[14]):null;
        Double mode=(campos[15]!="")? Double.parseDouble(campos[15]):null;
        Double speechiness=(campos[16]!="")? Double.parseDouble(campos[16]):null;
        Double acousticness=(campos[17]!="")? Double.parseDouble(campos[17]):null;
        Double instrumentalness=(campos[18]!="")? Double.parseDouble(campos[18]):null;
        Double liveness=(campos[19]!="")? Double.parseDouble(campos[19]):null;
        Double valence=(campos[20]!="")? Double.parseDouble(campos[20]):null;
        Double tempo=(campos[21]!="")? Double.parseDouble(campos[21]):null;
        Double duration_ms=(campos[22]!="")? Double.parseDouble(campos[22]):null;

        return new Musica(track_id, track_name,  track_artist,  track_popularity,
                track_album_id,  track_album_name,  track_album_release_date,  playlist_name,
                playlist_id,  playlist_genre,  playlist_subgenre,  danceability,  energy,
                key,  loudness,  mode,  speechiness,  acousticness,  instrumentalness,
                liveness,  valence,  tempo,  duration_ms);
    }

    @Override
    public List<Musica> findAllMusicas() {
        Path path = Path.of(ruta);
        try (CSVReader reader = new CSVReader(new FileReader(path.toString()))) {
            List<String[]> lines = reader.readAll();
            return lines.stream()
                    .skip(1)
                    .map(this::mapearMusica)
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
