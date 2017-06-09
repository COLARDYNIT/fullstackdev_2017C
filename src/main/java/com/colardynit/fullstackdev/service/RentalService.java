package com.colardynit.fullstackdev.service;

import com.colardynit.fullstackdev.domain.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Rental.
 */
public interface RentalService {

    /**
     * Save a rental.
     *
     * @param rental the entity to save
     * @return the persisted entity
     */
    Rental save(Rental rental);

    /**
     *  Get all the rentals.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Rental> findAll(Pageable pageable);

    /**
     *  Get the "id" rental.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Rental findOne(Long id);

    /**
     *  Delete the "id" rental.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
