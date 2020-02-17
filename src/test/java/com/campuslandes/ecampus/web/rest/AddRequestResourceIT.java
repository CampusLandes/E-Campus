package com.campuslandes.ecampus.web.rest;

import com.campuslandes.ecampus.ECampusApp;
import com.campuslandes.ecampus.domain.AddRequest;
import com.campuslandes.ecampus.repository.AddRequestRepository;
import com.campuslandes.ecampus.service.AddRequestService;
import com.campuslandes.ecampus.service.dto.AddRequestDTO;
import com.campuslandes.ecampus.service.mapper.AddRequestMapper;
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

import com.campuslandes.ecampus.domain.enumeration.InvitationStatus;
/**
 * Integration tests for the {@link AddRequestResource} REST controller.
 */
@SpringBootTest(classes = ECampusApp.class)
public class AddRequestResourceIT {

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final InvitationStatus DEFAULT_STATUS = InvitationStatus.WAIT;
    private static final InvitationStatus UPDATED_STATUS = InvitationStatus.ACCEPT;

    @Autowired
    private AddRequestRepository addRequestRepository;

    @Autowired
    private AddRequestMapper addRequestMapper;

    @Autowired
    private AddRequestService addRequestService;

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

    private MockMvc restAddRequestMockMvc;

    private AddRequest addRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddRequestResource addRequestResource = new AddRequestResource(addRequestService);
        this.restAddRequestMockMvc = MockMvcBuilders.standaloneSetup(addRequestResource)
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
    public static AddRequest createEntity(EntityManager em) {
        AddRequest addRequest = new AddRequest()
            .message(DEFAULT_MESSAGE)
            .status(DEFAULT_STATUS);
        return addRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AddRequest createUpdatedEntity(EntityManager em) {
        AddRequest addRequest = new AddRequest()
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS);
        return addRequest;
    }

    @BeforeEach
    public void initTest() {
        addRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddRequest() throws Exception {
        int databaseSizeBeforeCreate = addRequestRepository.findAll().size();

        // Create the AddRequest
        AddRequestDTO addRequestDTO = addRequestMapper.toDto(addRequest);
        restAddRequestMockMvc.perform(post("/api/add-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the AddRequest in the database
        List<AddRequest> addRequestList = addRequestRepository.findAll();
        assertThat(addRequestList).hasSize(databaseSizeBeforeCreate + 1);
        AddRequest testAddRequest = addRequestList.get(addRequestList.size() - 1);
        assertThat(testAddRequest.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testAddRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAddRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addRequestRepository.findAll().size();

        // Create the AddRequest with an existing ID
        addRequest.setId(1L);
        AddRequestDTO addRequestDTO = addRequestMapper.toDto(addRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddRequestMockMvc.perform(post("/api/add-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddRequest in the database
        List<AddRequest> addRequestList = addRequestRepository.findAll();
        assertThat(addRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = addRequestRepository.findAll().size();
        // set the field null
        addRequest.setStatus(null);

        // Create the AddRequest, which fails.
        AddRequestDTO addRequestDTO = addRequestMapper.toDto(addRequest);

        restAddRequestMockMvc.perform(post("/api/add-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addRequestDTO)))
            .andExpect(status().isBadRequest());

        List<AddRequest> addRequestList = addRequestRepository.findAll();
        assertThat(addRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAddRequests() throws Exception {
        // Initialize the database
        addRequestRepository.saveAndFlush(addRequest);

        // Get all the addRequestList
        restAddRequestMockMvc.perform(get("/api/add-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getAddRequest() throws Exception {
        // Initialize the database
        addRequestRepository.saveAndFlush(addRequest);

        // Get the addRequest
        restAddRequestMockMvc.perform(get("/api/add-requests/{id}", addRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(addRequest.getId().intValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAddRequest() throws Exception {
        // Get the addRequest
        restAddRequestMockMvc.perform(get("/api/add-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddRequest() throws Exception {
        // Initialize the database
        addRequestRepository.saveAndFlush(addRequest);

        int databaseSizeBeforeUpdate = addRequestRepository.findAll().size();

        // Update the addRequest
        AddRequest updatedAddRequest = addRequestRepository.findById(addRequest.getId()).get();
        // Disconnect from session so that the updates on updatedAddRequest are not directly saved in db
        em.detach(updatedAddRequest);
        updatedAddRequest
            .message(UPDATED_MESSAGE)
            .status(UPDATED_STATUS);
        AddRequestDTO addRequestDTO = addRequestMapper.toDto(updatedAddRequest);

        restAddRequestMockMvc.perform(put("/api/add-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addRequestDTO)))
            .andExpect(status().isOk());

        // Validate the AddRequest in the database
        List<AddRequest> addRequestList = addRequestRepository.findAll();
        assertThat(addRequestList).hasSize(databaseSizeBeforeUpdate);
        AddRequest testAddRequest = addRequestList.get(addRequestList.size() - 1);
        assertThat(testAddRequest.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testAddRequest.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingAddRequest() throws Exception {
        int databaseSizeBeforeUpdate = addRequestRepository.findAll().size();

        // Create the AddRequest
        AddRequestDTO addRequestDTO = addRequestMapper.toDto(addRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddRequestMockMvc.perform(put("/api/add-requests")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AddRequest in the database
        List<AddRequest> addRequestList = addRequestRepository.findAll();
        assertThat(addRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddRequest() throws Exception {
        // Initialize the database
        addRequestRepository.saveAndFlush(addRequest);

        int databaseSizeBeforeDelete = addRequestRepository.findAll().size();

        // Delete the addRequest
        restAddRequestMockMvc.perform(delete("/api/add-requests/{id}", addRequest.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AddRequest> addRequestList = addRequestRepository.findAll();
        assertThat(addRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
