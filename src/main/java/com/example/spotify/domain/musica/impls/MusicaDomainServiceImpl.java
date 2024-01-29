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
                .stream() //filtramos el con el nombre del artista y usamos equalsIsIgnoreCase para ignorar mayuscular si es que las hay
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
                .stream()//filtramos el con el nombre de la cancion y usamos equalsIsIgnoreCase para ignorar mayuscular si es que las hay
                .filter(musica -> musica.getTrack_name().equalsIgnoreCase(name))
                .toList();

    }

    @Override
    public Optional<Integer> findNumberTotalMusica() {
        log.info("Obteniendo el total de canciones");
        return  Optional.of((int) leerFuncional.getMusicas()
                .stream()
                .count()); // contamos la cantidad de datos, tambine se puso usar size()
    }

    @Override
    public Optional<Integer> findNumberTotalMusicaByArtist(String name) {
        log.info("Obteniendo el total de canciones por artista: [{}]", name);
        return Optional.of((int) findAllMusicaByArtist(name)
                .stream()
                .count());// contamos la cantidad de datos, tambine se puso usar size()
    }

    @Override
    public Optional<Integer> findNumberTotalArtist() {
        log.info("Obteniendo el total de artistas");
        return Optional.of((int) findAllArtist()
                .stream()
                .count());// contamos la cantidad de datos, tambine se puso usar size()
    }

    @Override
    public List<Musica> findAllMusicaByPopularity(Double popularity) {
        log.info("Obteniendo el total de canciones con popularidad mayor a: [{}]",popularity);
        return leerFuncional.getMusicas()
                .stream()// filtramos con la condicion de que sea mayor o igual a la variable popularity
                .filter(musica -> musica.getTrack_popularity() >= popularity)
                .toList();
    }

    @Override
    public List<Musica> findAllMusicaByDuration_ms(Double duration_ms) {
        log.info("Obteniendo el total de canciones con duracion mayor a: [{}]",duration_ms);
        return leerFuncional.getMusicas()
                .stream() // filtramos con la condicion de que sea mayor o igual a la conversion que se hizo de minitos a ms
                .filter(musica -> musica.getDuration_ms() >= (duration_ms*60*1000))
                .toList();
    }

    @Override
    public Optional<Musica> findMusicaEnergy() {
        log.info("Obteniendo la cancion con mayor energia");
        return leerFuncional.getMusicas() // Optenemos el max con la variable energy
                .stream().max(Comparator.comparing(Musica::getEnergy));
    }

    @Override
    public Optional<Musica> findMusicaPopularity() {
        log.info("Obteniendo la cancion mas popular");
        return leerFuncional.getMusicas() // Optenemos el max con la variable popularity
                .stream().max(Comparator.comparing(Musica::getTrack_popularity));
    }

    @Override
    public List<String> findAllGenre() {
        log.info("Obteniendo todos los generos");
                                        // Agrupamos y convertimos directamente en un lista
        List<Musica> musicasDistintas = leerFuncional.getMusicas().stream()
                .collect(Collectors.toMap(Musica::getPlaylist_genre,
                        Function.identity(),(existing,replacement)->existing)).values().stream().toList();

        List<String> generos = new ArrayList<>();
        for (Musica musica : musicasDistintas) {
            generos.add(musica.getPlaylist_genre()); // guardamos los datos con un forEach
        }
        return generos;
    }

    @Override
    public Optional<Integer> findNumberTotalGenre() {
        log.info("Obteniendo el total de generos");
        return Optional.of((int) findAllGenre().stream().count()); // contaos los datos
    }

    @Override
    public List<Musica> find10MostPopularMusica() {
        return leerFuncional.getMusicas()
                .stream()
                .sorted(Comparator.comparing(Musica::getTrack_popularity) // ordenamos  con la condicion que lo haga con popularidad
                        .reversed()).limit(10) //invertimos el flujo de datos
                .toList(); // convertimos en lista
    }

    @Override
    public Optional<Integer> findTotalGenreByArtista(String nameArtista) {
        log.info("Buscando cantidad de generos que tiene un artista [{}]",nameArtista);
        Map<String,Musica> musicaPorGenero = findAllMusicaByArtist(nameArtista).stream()
                .collect(Collectors.toMap(Musica::getPlaylist_genre,
                        Function.identity(),(existing,replacement)->existing));

        return Optional.of((int)musicaPorGenero.values().stream().count()) ;
       
    }

    @Override
    public Optional<Musica> findMusicSpecificOfArtist(String musicaName) {
        log.info("Buscando musica especifica de con nombre y artista [{}]",musicaName);
        return  leerFuncional.getMusicas()
                .stream()
                .filter(musica -> (musica.getTrack_name()+" "+musica.getTrack_artist()).equalsIgnoreCase(musicaName) ||
                        (musica.getTrack_artist()+" "+musica.getTrack_name()).equalsIgnoreCase(musicaName) )
                .findFirst();
    }

    @Override
    public List<String> findAllAlbums() {
        log.info("Obteniendo todos los generos");

        List<Musica> musicasDistintas = leerFuncional.getMusicas().stream()
                .collect(Collectors.toMap(Musica::getTrack_album_name,
                        Function.identity(),(existing,replacement)->existing)).values().stream().toList();

        List<String> album = new ArrayList<>();
        for (Musica musica : musicasDistintas) {
            album.add(musica.getTrack_album_name());
        }
        return album;

    }

    @Override
    public List<Musica> findAllMusicVoiceOff() {
        log.info("Obtenendo lista de canciones que no muy probablemente no contengan voces");

        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getInstrumentalness()>=0.5)
                .toList();
    }

    @Override
    public Optional<Musica> findMusicPopularityByArtist(String artist) {
        log.info("Buscando cancion mas popular de un artista: [{}]", artist);
        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getTrack_artist().equalsIgnoreCase(artist)).max(Comparator.comparing(Musica::getTrack_popularity));
    }

    @Override
    public List<Musica> findAllMusicByRangeTempo(Double[] tempo) {
        log.info("Obteniendo canciones con tempos entre: [{}] y [{}]",tempo[0],tempo[1]);
        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getTempo() >= tempo[0] && musica.getTempo() <= tempo[1])
                .toList();
    }

    @Override
    public Optional<Double> findTotalMinMusicaByArtist(String artist) {
        log.info("Obteniendo el total de tiempo de música por artista: [{}]", artist);

        // Suma de la duración total en milisegundos
        return Optional.of(findAllMusicaByArtist(artist).stream()
                .mapToDouble(Musica::getDuration_ms)
                .sum() / (60.0 * 1000.0)); //convertimos a minutos

    }

    @Override
    public Optional<Musica> findLongestMusicaByArtist(String artist) {
        log.info("Obteniendo la canción más larga de un artista: [{}]", artist);

        return findAllMusicaByArtist(artist)
                .stream().max(Comparator.comparingDouble(Musica::getDuration_ms));
    }

    @Override
    public List<String> findTheArtistWithTheMostMusic() {
        log.info("Obteniendo el artista con mayor cantidad de canciones");
        // Agrupar las canciones por el nombre del artista y contar el número de canciones para cada artista
        Map<String, Long> conteoCancionesPorArtista = leerFuncional.getMusicas()
                .stream()
                .collect(Collectors.groupingBy(Musica::getTrack_artist, Collectors.counting()));

        // Encontrar el artista con más canciones
        Map.Entry<String, Long> artistaConMasCanciones = conteoCancionesPorArtista
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        // Crear una lista de String con el nombre del artista y el número de canciones
        if (artistaConMasCanciones != null) {
            String nombreArtista = artistaConMasCanciones.getKey();
            long cantidadCanciones = artistaConMasCanciones.getValue();

            return List.of(nombreArtista, String.valueOf(cantidadCanciones));
        } else {
            return List.of("", ""); // por si en algun caso no hubiera nada en el csv
        }
    }

    @Override
    public Optional<Musica> findTheMostPopularSongByBenre(String genre) {
        log.info("Obten la cancion mas popular de un genero [{}]", genre);

        return leerFuncional.getMusicas()
                .stream()
                .filter(musica -> musica.getPlaylist_genre().equalsIgnoreCase(genre))
                .max(Comparator.comparingDouble(Musica::getTrack_popularity));
    }

    @Override
    public List<Musica> findSongsWithoutVocalsByArtist(String artist) {
        log.info("Obteniendo las canciones que no contengan voces de un artista: [{}]", artist);
        return findAllMusicaByArtist(artist)
                .stream()
                .filter(musica -> musica.getInstrumentalness()>=0.5)
                .toList();

    }

    @Override
    public List<String> findArtistByMusicVocals() {
        log.info("Obteniendo el artistas con canciones que no tienen voces");

        List<Musica> musicasDistintas = findAllMusicVoiceOff().stream()
                .collect(Collectors.toMap(Musica::getTrack_artist,
                        Function.identity(),(existing,replacement)->existing))
                .values().stream().toList();

        List<String> artist = new ArrayList<>();
        for (Musica musica : musicasDistintas) {
            artist.add(musica.getTrack_artist());
        }
        return artist;
    }

    @Override
    public Optional<Double> findTotalMinAllMusica() {
        log.info("Obteniendo el total de tiempo de música");

        // Suma de la duración total en milisegundos
        return Optional.of((leerFuncional.getMusicas().stream()
                .mapToDouble(Musica::getDuration_ms)
                .sum() / (60.0 * 1000.0))/60); //convertimos a horas
    }




}
