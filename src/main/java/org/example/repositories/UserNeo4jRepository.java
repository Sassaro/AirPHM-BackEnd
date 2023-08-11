package org.example.repositories;

import org.example.dataObjects.UserNeo4jDataObject;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UserNeo4jRepository extends Neo4jRepository<UserNeo4jDataObject,Long> {

    @Query("MATCH(u:UserNeo4jDataObject)-[:FRIEND_LIST]-(a:UserNeo4jDataObject)-[:reserved_by]-(r:ReservationNeo4jDataObject) WHERE u.id=$userId RETURN r.lodgingId")
    List<String> friendsLodgings(Long userId);

    @Query(" MATCH (u:UserNeo4jDataObject)<-[r:FRIEND_LIST]->(a:UserNeo4jDataObject) WHERE u.id = $userId AND a.id = $friendId delete r ")
    void deleteFriendship(Long userId, Long friendId);
}