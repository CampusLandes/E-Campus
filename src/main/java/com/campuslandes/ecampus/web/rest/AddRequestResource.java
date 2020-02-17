package com.campuslandes.ecampus.web.rest;

import com.campuslandes.ecampus.service.AddRequestService;
import com.campuslandes.ecampus.web.rest.errors.BadRequestAlertException;
import com.campuslandes.ecampus.service.dto.AddRequestDTO;

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
 * REST controller for managing {@link com.campuslandes.ecampus.domain.AddRequest}.
 */
@RestController
@RequestMapping("/api")
public class AddRequestResource {

    private final Logger log = LoggerFactory.getLogger(AddRequestResource.class);

    private static final String ENTITY_NAME = "addRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AddRequestService addRequestService;

    public AddRequestResource(AddRequestService addRequestService) {
        this.addRequestService = addRequestService;
    }

    /**
     * {@code POST  /add-requests} : Create a new addRequest.
     *
     * @param addRequestDTO the addRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new addRequestDTO, or with status {@code 400 (Bad Request)} if the addRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/add-requests")
    public ResponseEntity<AddRequestDTO> createAddRequest(@Valid @RequestBody AddRequestDTO addRequestDTO) throws URISyntaxException {
        log.debug("REST request to save AddRequest : {}", addRequestDTO);
        if (addRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new addRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AddRequestDTO result = addRequestService.save(addRequestDTO);
        return ResponseEntity.created(new URI("/api/add-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /add-requests} : Updates an existing addRequest.
     *
     * @param addRequestDTO the addRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated addRequestDTO,
     * or with status {@code 400 (Bad Request)} if the addRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the addRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/add-requests")
    public ResponseEntity<AddRequestDTO> updateAddRequest(@Valid @RequestBody AddRequestDTO addRequestDTO) throws URISyntaxException {
        log.debug("REST request to update AddRequest : {}", addRequestDTO);
        if (addRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AddRequestDTO result = addRequestService.save(addRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, addRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /add-requests} : get all the addRequests.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of addRequests in body.
     */
    @GetMapping("/add-requests")
    public ResponseEntity<List<AddRequestDTO>> getAllAddRequests(Pageable pageable) {
        log.debug("REST request to get a page of AddRequests");
        Page<AddRequestDTO> page = addRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /add-requests/:id} : get the "id" addRequest.
     *
     * @param id the id of the addRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the addRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/add-requests/{id}")
    public ResponseEntity<AddRequestDTO> getAddRequest(@PathVariable Long id) {
        log.debug("REST request to get AddRequest : {}", id);
        Optional<AddRequestDTO> addRequestDTO = addRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(addRequestDTO);
    }

    /**
     * {@code DELETE  /add-requests/:id} : delete the "id" addRequest.
     *
     * @param id the id of the addRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/add-requests/{id}")
    public ResponseEntity<Void> deleteAddRequest(@PathVariable Long id) {
        log.debug("REST request to delete AddRequest : {}", id);
        addRequestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
