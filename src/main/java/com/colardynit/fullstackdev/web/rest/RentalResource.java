package com.colardynit.fullstackdev.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.colardynit.fullstackdev.domain.Rental;
import com.colardynit.fullstackdev.service.RentalService;
import com.colardynit.fullstackdev.web.rest.util.HeaderUtil;
import com.colardynit.fullstackdev.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Rental.
 */
@RestController
@RequestMapping("/api")
public class RentalResource {

    private final Logger log = LoggerFactory.getLogger(RentalResource.class);

    private static final String ENTITY_NAME = "rental";

    private final RentalService rentalService;

    public RentalResource(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * POST  /rentals : Create a new rental.
     *
     * @param rental the rental to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rental, or with status 400 (Bad Request) if the rental has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/rentals")
    @Timed
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) throws URISyntaxException {
        log.debug("REST request to save Rental : {}", rental);
        if (rental.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new rental cannot already have an ID")).body(null);
        }
        Rental result = rentalService.save(rental);
        return ResponseEntity.created(new URI("/api/rentals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /rentals : Updates an existing rental.
     *
     * @param rental the rental to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rental,
     * or with status 400 (Bad Request) if the rental is not valid,
     * or with status 500 (Internal Server Error) if the rental couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/rentals")
    @Timed
    public ResponseEntity<Rental> updateRental(@RequestBody Rental rental) throws URISyntaxException {
        log.debug("REST request to update Rental : {}", rental);
        if (rental.getId() == null) {
            return createRental(rental);
        }
        Rental result = rentalService.save(rental);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, rental.getId().toString()))
            .body(result);
    }

    /**
     * GET  /rentals : get all the rentals.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of rentals in body
     */
    @GetMapping("/rentals")
    @Timed
    public ResponseEntity<List<Rental>> getAllRentals(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Rentals");
        Page<Rental> page = rentalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rentals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /rentals/:id : get the "id" rental.
     *
     * @param id the id of the rental to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rental, or with status 404 (Not Found)
     */
    @GetMapping("/rentals/{id}")
    @Timed
    public ResponseEntity<Rental> getRental(@PathVariable Long id) {
        log.debug("REST request to get Rental : {}", id);
        Rental rental = rentalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(rental));
    }

    /**
     * DELETE  /rentals/:id : delete the "id" rental.
     *
     * @param id the id of the rental to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/rentals/{id}")
    @Timed
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        log.debug("REST request to delete Rental : {}", id);
        rentalService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
