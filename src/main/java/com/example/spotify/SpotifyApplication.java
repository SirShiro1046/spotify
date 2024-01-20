package com.example.spotify;

import com.example.spotify.clases.CsvReader;
import com.example.spotify.clases.LeerFuncional;
import com.example.spotify.interfaces.IMusicaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyApplication.class, args);


    }

}
