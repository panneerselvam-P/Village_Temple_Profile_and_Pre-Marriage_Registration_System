package com.temple.marriage.repository;

import com.temple.marriage.model.MarriageRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarriageRegistrationRepository extends JpaRepository<MarriageRegistration, Long> {
}
