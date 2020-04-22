package com.campuslandes.ecampus.service.impl;

import com.campuslandes.ecampus.service.EventService;
import com.campuslandes.ecampus.domain.Event;
import com.campuslandes.ecampus.repository.EventRepository;
import com.campuslandes.ecampus.service.dto.EventDTO;
import com.campuslandes.ecampus.service.mapper.EventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Event}.
 */
@Service
@Transactional
public class EventServiceImpl implements EventService {

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    private static String UPLOADED_FOLDER = "/Jhipster/file/upload/Event";

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    /**
     * Save a event.
     *
     * @param eventDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EventDTO save(EventDTO eventDTO) {
        log.debug("Request to save Event : {}", eventDTO);
        Event event = eventMapper.toEntity(eventDTO);
        event = eventRepository.save(event);
        return eventMapper.toDto(event);
    }

    /**
     * Get all the events.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Events");
        return eventRepository.findAll(pageable)
            .map(eventMapper::toDto);
    }

    /**
     * Get all the events with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<EventDTO> findAllWithEagerRelationships(Pageable pageable) {
        return eventRepository.findAllWithEagerRelationships(pageable).map(eventMapper::toDto);
    }


    /**
     * Get one event by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EventDTO> findOne(Long id) {
        log.debug("Request to get Event : {}", id);
        return eventRepository.findOneWithEagerRelationships(id)
            .map(eventMapper::toDto);
    }

    /**
     * Delete the event by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Event : {}", id);
        eventRepository.deleteById(id);
    }



    @Scheduled(cron="0 1 1 * * *")
    public void cleanNotUseFile () {
        File folder = new File(UPLOADED_FOLDER);
        File[] listOfFiles = folder.listFiles();

        List<Event> events = eventRepository.findAll();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println("File " + file.getName());
            } else if (file.isDirectory()) {
                System.out.println("Directory " + file.getName());
                List<Event> eventsForNameFolder = events.stream().filter(event -> event.getResponsible().getLogin().equals(file.getName())).collect(Collectors.toList());
                File[] listOfFiles2 = file.listFiles();
                for(File file2 : listOfFiles2) {
                    if (!eventsForNameFolder.stream().filter(o -> o.getImageUrl().equals(file2.getName())).findFirst().isPresent()) {
                        System.out.println("Delete file " + file2.getName());
                        deleteFile(file2);
                    }
                }
            }
        }

    }

    public void deleteFile(File file) {
        try
        {
            Files.deleteIfExists(Paths.get(file.getPath()));
        }
        catch(NoSuchFileException e)
        {
            log.debug("No such file/directory exists");
        }
        catch(DirectoryNotEmptyException e)
        {
            log.debug("Directory is not empty.");
        }
        catch(IOException e)
        {
            log.debug("Invalid permissions.");
        }
        log.debug("Deletion successful.");
    }
}
