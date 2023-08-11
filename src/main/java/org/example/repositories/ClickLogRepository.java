package org.example.repositories;

import org.example.domain.ClickLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClickLogRepository extends MongoRepository<ClickLog,String> {

    Page<ClickLog> findClickLogByIdUsuarioOrderByFechaDesc(Long userId, Pageable pageable);

}
