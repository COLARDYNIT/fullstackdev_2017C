package com.colardynit.fullstackdev.web.rest;

import com.colardynit.fullstackdev.Fullstackdev2017CApp;
import com.colardynit.fullstackdev.domain.Rental;
import com.colardynit.fullstackdev.repository.RentalRepository;
import com.colardynit.fullstackdev.web.rest.errors.ExceptionTranslator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for the RentalResource REST controller.
 *
 * @see RentalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Fullstackdev2017CApp.class)
public class RentalResourceIntTest {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRentalMockMvc;

    private Rental rental;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RentalResource rentalResource = new RentalResource();
        this.restRentalMockMvc = MockMvcBuilders.standaloneSetup(rentalResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rental createEntity(EntityManager em) {
        Rental rental = new Rental()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE);
        return rental;
    }

    @Before
    public void initTest() {
        rental = createEntity(em);
    }

    @Test
    @Transactional
    public void createRentalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rentalRepository.findAll().size();

        // Create the Rental with an existing ID
        rental.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRentalMockMvc.perform(post("/api/rentals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rental)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Rental> rentalList = rentalRepository.findAll();
        assertThat(rentalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rental.class);
        Rental rental1 = new Rental();
        rental1.setId(1L);
        Rental rental2 = new Rental();
        rental2.setId(rental1.getId());
        assertThat(rental1).isEqualTo(rental2);
        rental2.setId(2L);
        assertThat(rental1).isNotEqualTo(rental2);
        rental1.setId(null);
        assertThat(rental1).isNotEqualTo(rental2);
    }
}
