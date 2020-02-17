package com.campuslandes.ecampus.web.rest;

import com.campuslandes.ecampus.service.TripEvaluationService;
import com.campuslandes.ecampus.web.rest.errors.BadRequestAlertException;
import com.campuslandes.ecampus.service.dto.TripEvaluationDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.campuslandes.ecampus.domain.TripEvaluation}.
 */
@RestController
@RequestMapping("/api")
public class TripEvaluationResource {

    private final Logger log = LoggerFactory.getLogger(TripEvaluationResource.class);

    private static final String ENTITY_NAME = "tripEvaluation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TripEvaluationService tripEvaluationService;

    public TripEvaluationResource(TripEvaluationService tripEvaluationService) {
        this.tripEvaluationService = tripEvaluationService;
    }

    /**
     * {@code POST  /trip-evaluations} : Create a new tripEvaluation.
     *
     * @param tripEvaluationDTO the tripEvaluationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tripEvaluationDTO, or with status {@code 400 (Bad Request)} if the tripEvaluation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/trip-evaluations")
    public ResponseEntity<TripEvaluationDTO> createTripEvaluation(@Valid @RequestBody TripEvaluationDTO tripEvaluationDTO) throws URISyntaxException {
        log.debug("REST request to save TripEvaluation : {}", tripEvaluationDTO);
        if (tripEvaluationDTO.getId() != null) {
            throw new BadRequestAlertException("A new tripEvaluation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TripEvaluationDTO result = tripEvaluationService.save(tripEvaluationDTO);
        return ResponseEntity.created(new URI("/api/trip-evaluations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /trip-evaluations} : Updates an existing tripEvaluation.
     *
     * @param tripEvaluationDTO the tripEvaluationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tripEvaluationDTO,
     * or with status {@code 400 (Bad Request)} if the tripEvaluationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tripEvaluationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/trip-evaluations")
    public ResponseEntity<TripEvaluationDTO> updateTripEvaluation(@Valid @RequestBody TripEvaluationDTO tripEvaluationDTO) throws URISyntaxException {
        log.debug("REST request to update TripEvaluation : {}", tripEvaluationDTO);
        if (tripEvaluationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TripEvaluationDTO result = tripEvaluationService.save(tripEvaluationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tripEvaluationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /trip-evaluations} : get all the tripEvaluations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tripEvaluations in body.
     */
    @GetMapping("/trip-evaluations")
    public ResponseEntity<List<TripEvaluationDTO>> getAllTripEvaluations(Pageable pageable) {
        log.debug("REST request to get a page of TripEvaluations");
        Page<TripEvaluationDTO> page = tripEvaluationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /trip-evaluations/:id} : get the "id" tripEvaluation.
     *
     * @param id the id of the tripEvaluationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tripEvaluationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/trip-evaluations/{id}")
    public ResponseEntity<TripEvaluationDTO> getTripEvaluation(@PathVariable Long id) {
        log.debug("REST request to get TripEvaluation : {}", id);
        Optional<TripEvaluationDTO> tripEvaluationDTO = tripEvaluationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tripEvaluationDTO);
    }

    /**
     * {@code DELETE  /trip-evaluations/:id} : delete the "id" tripEvaluation.
     *
     * @param id the id of the tripEvaluationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/trip-evaluations/{id}")
    public ResponseEntity<Void> deleteTripEvaluation(@PathVariable Long id) {
        log.debug("REST request to delete TripEvaluation : {}", id);
        tripEvaluationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
