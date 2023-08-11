package org.example.repositories;

import org.example.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    @EntityGraph(attributePaths = {"creator"})
    List<Comment> findCommentByLodgingId(String lodgingId);

    List<Comment> findCommentByCreator_Id(Long creator_id);

    List<Comment> findCommentByLodgingIdAndCreator_Id(String lodgingId, Long creator_id);

    @Query("""
        SELECT COALESCE(AVG(c.score),0.0) as avgScore, c.lodgingId as idHosp FROM Comment c
        GROUP BY c.lodgingId
        HAVING c.lodgingId = :lodgingId
    """)
    Optional<Float> findAverageScoreById(String lodgingId);


}
