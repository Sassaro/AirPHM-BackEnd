package org.example.repositories;

import org.example.dataObjects.ReservationNeo4jDataObject;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ReservationNeo4jRepository extends Neo4jRepository<ReservationNeo4jDataObject,Long> {
}
