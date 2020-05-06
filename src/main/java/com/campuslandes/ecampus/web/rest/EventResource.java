package com.campuslandes.ecampus.web.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.campuslandes.ecampus.domain.Event;
import com.campuslandes.ecampus.domain.User;
import com.campuslandes.ecampus.repository.EventRepository;
import com.campuslandes.ecampus.repository.UserRepository;
import com.campuslandes.ecampus.security.SecurityUtils;
import com.campuslandes.ecampus.service.EventService;
import com.campuslandes.ecampus.service.dto.EventDTO;
import com.campuslandes.ecampus.service.mapper.EventMapper;
import com.campuslandes.ecampus.web.rest.errors.BadRequestAlertException;
import com.campuslandes.ecampus.web.rest.utils.uploadFileUtils;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.campuslandes.ecampus.domain.Event}.
 */
@RestController
@RequestMapping("/api")
public class EventResource {

    private final Logger log = LoggerFactory.getLogger(EventResource.class);

    private static final String ENTITY_NAME = "event";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EventService eventService;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final EventMapper eventMapper;

    public EventResource(EventService eventService, EventRepository eventRepository, EventMapper eventMapper,
            UserRepository userRepository) {
        this.eventService = eventService;
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.userRepository = userRepository;
    }

    /**
     * {@code POST  /events} : Create a new event.
     *
     * @param eventDTO the eventDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new eventDTO, or with status {@code 400 (Bad Request)} if
     *         the event has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/events")
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to save Event : {}", eventDTO);
        if (eventDTO.getId() != null) {
            throw new BadRequestAlertException("A new event cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EventDTO result = eventService.save(eventDTO);
        return ResponseEntity
                .created(new URI("/api/events/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /events} : Updates an existing event.
     *
     * @param eventDTO the eventDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated eventDTO, or with status {@code 400 (Bad Request)} if the
     *         eventDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the eventDTO couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/events")
    public ResponseEntity<EventDTO> updateEvent(@Valid @RequestBody EventDTO eventDTO) throws URISyntaxException {
        log.debug("REST request to update Event : {}", eventDTO);
        if (eventDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EventDTO result = eventService.save(eventDTO);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, eventDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /events} : get all the events.
     *
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is
     *                  applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of events in body.
     */
    @GetMapping("/events")
    public ResponseEntity<List<EventDTO>> getAllEvents(Pageable pageable,
            @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Events");
        Page<EventDTO> page;
        if (eagerload) {
            page = eventService.findAllWithEagerRelationships(pageable);
        } else {
            page = eventService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/events/upload/")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        log.debug("REST request to upload File");
        return uploadFileUtils.uploadOneImage(file, "Event");
    }

    @GetMapping("/events/image/{image}")
    public byte[] getEventsImage(@PathVariable String image) throws IOException {
        log.debug("REST request to get a page of Events");
        InputStream in = uploadFileUtils.getOneImage(image, "Event");
        if (in == null) {
            in = getClass().getResourceAsStream("resources/NoImage.png");
        }
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/events/10LastPublic")
    public ResponseEntity<List<EventDTO>> get10LastEvents() {
        log.debug("REST request to get a page of Events");
        List<Event> events = eventRepository.findAllPublic();
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event u1, Event u2) {
                return u1.getCompletionDate().compareTo(u2.getCompletionDate());
            }
        });
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (int i = 0; i < events.size() && i < 10; i++) {
            if (events.get(i).getCompletionDate().isAfter(Instant.now())) {
                events.get(i).getResponsible().setAuthorities(new HashSet<>());
                for (User usertmp : events.get(i).getParticipants()) {
                    usertmp.setAuthorities(new HashSet<>());
                }
                eventDTOs.add(eventMapper.toDto(events.get(i)));
            }
        }
        return ResponseEntity.ok().body(eventDTOs);
    }

    @GetMapping("/events/10LastPublicAndPrivate")
    public ResponseEntity<List<EventDTO>> get10LastEventsPrivate() {
        log.debug("REST request to get a page of Events");
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        List<Event> events = eventRepository.findAllPublicAndCompletionDateExist();
        List<Event> eventsPrivate = eventRepository.findAllPrivateAndCompletionDateExist();
        events.addAll(eventsPrivate.stream()
        .filter(event -> event.getParticipants().contains(user) || event.getResponsible().equals(user))
        .collect(Collectors.toList()));
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event u1, Event u2) {
                return u1.getCompletionDate().compareTo(u2.getCompletionDate()); 
            }
        });
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (int i = 0; i < events.size() && i < 10; i++) {
            if (events.get(i).getCompletionDate().isAfter(Instant.now())) {
                events.get(i).getResponsible().setAuthorities(new HashSet<>());
                for (User usertmp : events.get(i).getParticipants()) {
                    usertmp.setAuthorities(new HashSet<>());
                }
                eventDTOs.add(eventMapper.toDto(events.get(i)));
            }
        }
        return ResponseEntity.ok().body(eventDTOs);
    }


        /**
     * {@code GET  /events} : get all the events.
     *
     *
     * @param pageable  the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is
     *                  applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of events in body.
     */
    @GetMapping("/events/publicAndPrivateWhereUser")
    public ResponseEntity<List<EventDTO>> publicAndPrivateWhereUser(Pageable pageable,
            @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of Events");
        // On initialise la page de retour
        Page<EventDTO> page;
        // On recupère le user connecté
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        // On recupère les listes de tous les events
        List<Event> events = eventRepository.findAllPublic();
        List<Event> eventsPrivate = eventRepository.findAllPrivate();
        // On ajoute à la liste des events public les events privé dont le user fait parti
        events.addAll(eventsPrivate.stream()
        .filter(event -> event.getParticipants().contains(user) || event.getResponsible().equals(user))
        .collect(Collectors.toList()));
        // On tri la,liste des events en fonction de leur datede creation
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event u1, Event u2) {
                return u1.getCreatedDate().compareTo(u2.getCreatedDate()); 
            }
        });
        // On transforme la totalité de nos events en DTO pour pouvoir les utiliser plus facilement pour le frontEnd
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            event.getResponsible().setAuthorities(new HashSet<>());
            for (User usertmp : event.getParticipants()) {
                usertmp.setAuthorities(new HashSet<>());
            }
            eventDTOs.add(eventMapper.toDto(event));
        }
        // On récupère les params de pagination
        int size = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int posDeb = size * pageNumber;
        int posFin = size * (pageNumber+1);
        // On s'assure de ne pas avoir d'erreur lorq de la récupération des données
        if (posFin>events.size()) {
            posFin = events.size();
        }
        if (posDeb>events.size()) {
            page = new PageImpl<EventDTO>(new ArrayList<>());
        } else {
            // On initialise la page avec les données voulues
            page = new PageImpl<EventDTO>(eventDTOs.subList(posDeb, posFin));
            
        }
        // Permet de faire le retour avec un header custom
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
















    /**
     * {@code GET  /events/:id} : get the "id" event.
     *
     * @param id the id of the eventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the eventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable Long id) {
        log.debug("REST request to get Event : {}", id);
        Optional<EventDTO> eventDTO = eventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(eventDTO);
    }

    /**
     * {@code DELETE  /events/:id} : delete the "id" event.
     *
     * @param id the id of the eventDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        log.debug("REST request to delete Event : {}", id);
        eventService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }
}
