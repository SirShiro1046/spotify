package com.example.spotify.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Musica {
    private String track_id;
    private String track_name;
    private String track_artist;
    private Double track_popularity;
    private String track_album_id;
    private String track_album_name;
    private String track_album_release_date;
    private String playlist_name;
    private String playlist_id;
    private String playlist_genre;
    private String playlist_subgenre;
    private Double danceability;
    private Double energy;
    private Double key;
    private Double loudness;
    private Double mode;
    private Double speechiness;
    private Double acousticness;
    private Double instrumentalness;
    private Double liveness;
    private Double valence;
    private Double tempo;
    private Double duration_ms;
}
