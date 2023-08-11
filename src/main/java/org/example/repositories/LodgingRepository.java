package org.example.repositories;

import org.example.domain.lodging.Lodging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

public interface LodgingRepository extends MongoRepository<Lodging,String> {

    List<Lodging> findByCreator_IdAndActiveTrue(Long creator_id);

    Page<Lodging> findLodgingByLocation_CountryLikeAndCapacityGreaterThanEqualAndAvgScoreGreaterThanEqualAndIdNotInAndActiveTrue(
            String location_country, int capacity, float avgScore, Collection<String> id, Pageable pageable);

    Page<Lodging> findLodgingByIdInAndActiveTrue(List<String> id, Pageable pageable);
}
