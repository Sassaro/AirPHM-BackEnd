package org.example.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash(timeToLive = 60)
public class LodgingSearch {
    @Id
    String id;
    public String resultadoBusqueda;

    public LodgingSearch(String id, String searchResult) {
        this.id = id;
        this.resultadoBusqueda = searchResult;
    }
}


