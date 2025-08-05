package com.games.games.models.repositories;

import com.games.games.models.entities.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {
}