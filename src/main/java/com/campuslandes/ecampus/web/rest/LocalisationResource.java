package com.campuslandes.ecampus.web.rest;

import com.campuslandes.ecampus.service.LocalisationService;
import com.campuslandes.ecampus.web.rest.errors.BadRequestAlertException;
import com.campuslandes.ecampus.service.dto.LocalisationDTO;

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
 * REST controller for managing {@link com.campuslandes.ecampus.domain.Localisation}.
 */
@RestController
@RequestMapping("/api")
public class LocalisationResource {

    private final Logger log = LoggerFactory.getLogger(LocalisationResource.class);

    private static final String ENTITY_NAME = "localisation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocalisationService localisationService;

    public LocalisationResource(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    /**
     * {@code POST  /localisations} : Create a new localisation.
     *
     * @param localisationDTO the localisationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new localisationDTO, or with status {@code 400 (Bad Request)} if the localisation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/localisations")
    public ResponseEntity<LocalisationDTO> createLocalisation(@Valid @RequestBody LocalisationDTO localisationDTO) throws URISyntaxException {
        log.debug("REST request to save Localisation : {}", localisationDTO);
        if (localisationDTO.getId() != null) {
            throw new BadRequestAlertException("A new localisation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocalisationDTO result = localisationService.save(localisationDTO);
        return ResponseEntity.created(new URI("/api/localisations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /localisations} : Updates an existing localisation.
     *
     * @param localisationDTO the localisationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localisationDTO,
     * or with status {@code 400 (Bad Request)} if the localisationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the localisationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/localisations")
    public ResponseEntity<LocalisationDTO> updateLocalisation(@Valid @RequestBody LocalisationDTO localisationDTO) throws URISyntaxException {
        log.debug("REST request to update Localisation : {}", localisationDTO);
        if (localisationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LocalisationDTO result = localisationService.save(localisationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localisationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /localisations} : get all the localisations.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localisations in body.
     */
    @GetMapping("/localisations")
    public ResponseEntity<List<LocalisationDTO>> getAllLocalisations(Pageable pageable) {
        log.debug("REST request to get a page of Localisations");
        Page<LocalisationDTO> page = localisationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /localisations/:id} : get the "id" localisation.
     *
     * @param id the id of the localisationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localisationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/localisations/{id}")
    public ResponseEntity<LocalisationDTO> getLocalisation(@PathVariable Long id) {
        log.debug("REST request to get Localisation : {}", id);
        Optional<LocalisationDTO> localisationDTO = localisationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localisationDTO);
    }

    /**
     * {@code DELETE  /localisations/:id} : delete the "id" localisation.
     *
     * @param id the id of the localisationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/localisations/{id}")
    public ResponseEntity<Void> deleteLocalisation(@PathVariable Long id) {
        log.debug("REST request to delete Localisation : {}", id);
        localisationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
