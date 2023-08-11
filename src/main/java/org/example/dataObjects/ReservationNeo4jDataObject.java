package org.example.dataObjects;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;

@Node
public class ReservationNeo4jDataObject {
    @Id
    Long id;
    LocalDate startDate;
    LocalDate endDate;

    @Relationship(type = "reserved_by", direction = Relationship.Direction.INCOMING)
    UserNeo4jDataObject user;
    String lodgingId;

    public ReservationNeo4jDataObject(Long id, LocalDate startDate, LocalDate endDate, UserNeo4jDataObject user, String lodgingId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.lodgingId = lodgingId;
    }
}
