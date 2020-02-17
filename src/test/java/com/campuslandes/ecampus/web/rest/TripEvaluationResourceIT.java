package com.campuslandes.ecampus.web.rest;

import com.campuslandes.ecampus.ECampusApp;
import com.campuslandes.ecampus.domain.TripEvaluation;
import com.campuslandes.ecampus.repository.TripEvaluationRepository;
import com.campuslandes.ecampus.service.TripEvaluationService;
import com.campuslandes.ecampus.service.dto.TripEvaluationDTO;
import com.campuslandes.ecampus.service.mapper.TripEvaluationMapper;
import com.campuslandes.ecampus.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.campuslandes.ecampus.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.campuslandes.ecampus.domain.enumeration.UserType;
/**
 * Integration tests for the {@link TripEvaluationResource} REST controller.
 */
@SpringBootTest(classes = ECampusApp.class)
public class TripEvaluationResourceIT {

    private static final Integer DEFAULT_NOTE = 1;
    private static final Integer UPDATED_NOTE = 2;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final UserType DEFAULT_TYPE = UserType.CONDUCTOR;
    private static final UserType UPDATED_TYPE = UserType.PASSENGER;

    @Autowired
    private TripEvaluationRepository tripEvaluationRepository;

    @Autowired
    private TripEvaluationMapper tripEvaluationMapper;

    @Autowired
    private TripEvaluationService tripEvaluationService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTripEvaluationMockMvc;

    private TripEvaluation tripEvaluation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TripEvaluationResource tripEvaluationResource = new TripEvaluationResource(tripEvaluationService);
        this.restTripEvaluationMockMvc = MockMvcBuilders.standaloneSetup(tripEvaluationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TripEvaluation createEntity(EntityManager em) {
        TripEvaluation tripEvaluation = new TripEvaluation()
            .note(DEFAULT_NOTE)
            .comment(DEFAULT_COMMENT)
            .type(DEFAULT_TYPE);
        return tripEvaluation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TripEvaluation createUpdatedEntity(EntityManager em) {
        TripEvaluation tripEvaluation = new TripEvaluation()
            .note(UPDATED_NOTE)
            .comment(UPDATED_COMMENT)
            .type(UPDATED_TYPE);
        return tripEvaluation;
    }

    @BeforeEach
    public void initTest() {
        tripEvaluation = createEntity(em);
    }

    @Test
    @Transactional
    public void createTripEvaluation() throws Exception {
        int databaseSizeBeforeCreate = tripEvaluationRepository.findAll().size();

        // Create the TripEvaluation
        TripEvaluationDTO tripEvaluationDTO = tripEvaluationMapper.toDto(tripEvaluation);
        restTripEvaluationMockMvc.perform(post("/api/trip-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tripEvaluationDTO)))
            .andExpect(status().isCreated());

        // Validate the TripEvaluation in the database
        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeCreate + 1);
        TripEvaluation testTripEvaluation = tripEvaluationList.get(tripEvaluationList.size() - 1);
        assertThat(testTripEvaluation.getNote()).isEqualTo(DEFAULT_NOTE);
        assertThat(testTripEvaluation.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testTripEvaluation.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createTripEvaluationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tripEvaluationRepository.findAll().size();

        // Create the TripEvaluation with an existing ID
        tripEvaluation.setId(1L);
        TripEvaluationDTO tripEvaluationDTO = tripEvaluationMapper.toDto(tripEvaluation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTripEvaluationMockMvc.perform(post("/api/trip-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tripEvaluationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TripEvaluation in the database
        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNoteIsRequired() throws Exception {
        int databaseSizeBeforeTest = tripEvaluationRepository.findAll().size();
        // set the field null
        tripEvaluation.setNote(null);

        // Create the TripEvaluation, which fails.
        TripEvaluationDTO tripEvaluationDTO = tripEvaluationMapper.toDto(tripEvaluation);

        restTripEvaluationMockMvc.perform(post("/api/trip-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tripEvaluationDTO)))
            .andExpect(status().isBadRequest());

        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tripEvaluationRepository.findAll().size();
        // set the field null
        tripEvaluation.setType(null);

        // Create the TripEvaluation, which fails.
        TripEvaluationDTO tripEvaluationDTO = tripEvaluationMapper.toDto(tripEvaluation);

        restTripEvaluationMockMvc.perform(post("/api/trip-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tripEvaluationDTO)))
            .andExpect(status().isBadRequest());

        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTripEvaluations() throws Exception {
        // Initialize the database
        tripEvaluationRepository.saveAndFlush(tripEvaluation);

        // Get all the tripEvaluationList
        restTripEvaluationMockMvc.perform(get("/api/trip-evaluations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tripEvaluation.getId().intValue())))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }
    
    @Test
    @Transactional
    public void getTripEvaluation() throws Exception {
        // Initialize the database
        tripEvaluationRepository.saveAndFlush(tripEvaluation);

        // Get the tripEvaluation
        restTripEvaluationMockMvc.perform(get("/api/trip-evaluations/{id}", tripEvaluation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tripEvaluation.getId().intValue()))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTripEvaluation() throws Exception {
        // Get the tripEvaluation
        restTripEvaluationMockMvc.perform(get("/api/trip-evaluations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTripEvaluation() throws Exception {
        // Initialize the database
        tripEvaluationRepository.saveAndFlush(tripEvaluation);

        int databaseSizeBeforeUpdate = tripEvaluationRepository.findAll().size();

        // Update the tripEvaluation
        TripEvaluation updatedTripEvaluation = tripEvaluationRepository.findById(tripEvaluation.getId()).get();
        // Disconnect from session so that the updates on updatedTripEvaluation are not directly saved in db
        em.detach(updatedTripEvaluation);
        updatedTripEvaluation
            .note(UPDATED_NOTE)
            .comment(UPDATED_COMMENT)
            .type(UPDATED_TYPE);
        TripEvaluationDTO tripEvaluationDTO = tripEvaluationMapper.toDto(updatedTripEvaluation);

        restTripEvaluationMockMvc.perform(put("/api/trip-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tripEvaluationDTO)))
            .andExpect(status().isOk());

        // Validate the TripEvaluation in the database
        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeUpdate);
        TripEvaluation testTripEvaluation = tripEvaluationList.get(tripEvaluationList.size() - 1);
        assertThat(testTripEvaluation.getNote()).isEqualTo(UPDATED_NOTE);
        assertThat(testTripEvaluation.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testTripEvaluation.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTripEvaluation() throws Exception {
        int databaseSizeBeforeUpdate = tripEvaluationRepository.findAll().size();

        // Create the TripEvaluation
        TripEvaluationDTO tripEvaluationDTO = tripEvaluationMapper.toDto(tripEvaluation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTripEvaluationMockMvc.perform(put("/api/trip-evaluations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tripEvaluationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TripEvaluation in the database
        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTripEvaluation() throws Exception {
        // Initialize the database
        tripEvaluationRepository.saveAndFlush(tripEvaluation);

        int databaseSizeBeforeDelete = tripEvaluationRepository.findAll().size();

        // Delete the tripEvaluation
        restTripEvaluationMockMvc.perform(delete("/api/trip-evaluations/{id}", tripEvaluation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TripEvaluation> tripEvaluationList = tripEvaluationRepository.findAll();
        assertThat(tripEvaluationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
