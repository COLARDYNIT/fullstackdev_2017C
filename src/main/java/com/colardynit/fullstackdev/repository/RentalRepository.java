package com.colardynit.fullstackdev.repository;

import com.colardynit.fullstackdev.domain.Rental;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Rental entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {

}
