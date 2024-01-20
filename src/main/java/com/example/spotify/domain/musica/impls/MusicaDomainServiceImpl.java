package com.example.spotify.domain.musica.impls;

import com.example.spotify.clases.LeerFuncional;
import com.example.spotify.domain.musica.IMusicaDomainService;
import com.example.spotify.persistence.entities.Musica;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class MusicaDomainServiceImpl implements IMusicaDomainService {
    private LeerFuncional leerFuncional = new LeerFuncional();
    @Override
    public List<Musica> findAllMusica() {
        log.info("Obteniendo primeras diez canciones");
        List<Musica> musicaList = new ArrayList<Musica>();
        leerFuncional.getMusicas().stream()
                .forEach(musica-> musicaList.add(musica));
        return musicaList;
    }

}
