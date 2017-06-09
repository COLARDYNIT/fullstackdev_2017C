package com.colardynit.fullstackdev.service.impl;

import com.colardynit.fullstackdev.service.RentalService;
import com.colardynit.fullstackdev.domain.Rental;
import com.colardynit.fullstackdev.repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Service Implementation for managing Rental.
 */
@Service
@Transactional
public class RentalServiceImpl implements RentalService{

    private final Logger log = LoggerFactory.getLogger(RentalServiceImpl.class);
    @Resource
    private RentalRepository rentalRepository;

    /**
     * Save a rental.
     *
     * @param rental the entity to save
     * @return the persisted entity
     */
    @Override
    public Rental save(Rental rental) {
        log.debug("Request to save Rental : {}", rental);
        return rentalRepository.save(rental);
    }

    /**
     *  Get all the rentals.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Rental> findAll(Pageable pageable) {
        log.debug("Request to get all Rentals");
        return rentalRepository.findAll(pageable);
    }

    /**
     *  Get one rental by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Rental findOne(Long id) {
        log.debug("Request to get Rental : {}", id);
        return rentalRepository.findOne(id);
    }

    /**
     *  Delete the  rental by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Rental : {}", id);
        rentalRepository.delete(id);
    }
}
