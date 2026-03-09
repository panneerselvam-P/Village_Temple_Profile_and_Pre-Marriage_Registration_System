package com.temple.marriage.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MarriageDocumentRepository extends JpaRepository<MarriageDocument, Long> {
    Optional<MarriageDocument> findByApplicationNumber(String applicationNumber);
}
