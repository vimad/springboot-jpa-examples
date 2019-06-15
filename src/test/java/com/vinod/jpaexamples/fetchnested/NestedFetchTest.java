package com.vinod.jpaexamples.fetchnested;

import com.vinod.jpaexamples.JpaExamplesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Subgraph;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaExamplesApplication.class)
public class NestedFetchTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NestedFetchTest.class);

    @Autowired
    EntityManager em;

    @Autowired
    ReservationRepository reservationRepository;

    @Test
    @Transactional
    public void repoTest(){
        List<Reservation> reservation = reservationRepository.findAll();
        LOGGER.info("Found reservation {}", reservation);
//        LOGGER.info("Patient is {}", reservation.get(0).getPatients().get(0));
    }

    @Test
    @Transactional
    public void lazyNestedFetch(){
        Reservation reservation = (Reservation) em.createQuery("select a from Reservation a where reservationId = 1L").getSingleResult();
        LOGGER.info("Reservation is {}", reservation);
        reservation.getPatients().forEach( e-> LOGGER.info("Patient is {}", e));
        reservation.getResources().forEach( e-> {
            LOGGER.info("resource is {}", e);
            LOGGER.info("hirarchy is {}", e.getHierarchy());
        });
    }

    @Test
    @Transactional
    public void nestedFetchingWithQuery(){
        Map<String, Object> params = new LinkedHashMap<>();
        StringBuilder queryContent = new StringBuilder();
        try {
            queryContent
                    .append(" select a from Reservation a join fetch a.resources r join fetch a.patients p join fetch r.hierarchy h")
                    .append(" where a.category in :category ")
                    .append(" and h.hierarchyId = :hierarchyId")
                    .append(" and h.hierarchyType = :hierarchyType")
                    .append(" and (p.patientId = :patientId")
                    .append(" or (r.resourceId = :primaryResourceId and r.resourceType = :primaryResourceType)")
                    .append(" or (r.resourceId = :resourceId and r.resourceType = :resourceType))");

            params.put("patientId", 4L);
            params.put("category", "OPERATING_ROOM");
            params.put("hierarchyId", 3L);
            params.put("hierarchyType", "ROOM");
            params.put("primaryResourceId", 1L);
            params.put("primaryResourceType", "DOCTOR");
            params.put("resourceId", 4L);
            params.put("resourceType", "OPERATING_ROOM");

            Query query = em.createQuery(queryContent.toString());
            params.entrySet().forEach(e -> query.setParameter(e.getKey(), e.getValue()));

            List<Reservation> reservations = query.getResultList();

            for(Reservation reservation: reservations){
                LOGGER.info("Reservation is {}", reservation);
                reservation.getPatients().forEach( e-> LOGGER.info("Patient is {}", e));
                reservation.getResources().forEach( e-> {
                    LOGGER.info("resource is {}", e);
                    LOGGER.info("hirarchy is {}", e.getHierarchy());
                });
            }

        }catch (Exception e){
            LOGGER.error("{}", e);
        }
    }

    @Test
    @Transactional
    public void nestedFetchingWithNamedEntityGraph(){
        EntityGraph graph = em.createEntityGraph("graph.all");
        Reservation reservation = (Reservation) em.createQuery("select distinct a from Reservation a where reservationId = 1L")
                .setHint("javax.persistence.loadgraph", graph)
                .getSingleResult();
        LOGGER.info("Reservation is {}", reservation);
        reservation.getPatients().forEach( e-> LOGGER.info("Patient is {}", e));
        reservation.getResources().forEach( e-> {
            LOGGER.info("resource is {}", e);
            LOGGER.info("hirarchy is {}", e.getHierarchy());
        });
    }

    @Test
    @Transactional
    public void nestedFetchingWithDynamicEntityGraph(){
        EntityGraph graph = em.createEntityGraph(Reservation.class);
        Subgraph<Patient> patientSubgraph = graph.addSubgraph("patients");
        Subgraph<Resource> resourceSubgraph = graph.addSubgraph("resources");
        resourceSubgraph.addSubgraph("hierarchy");

        Reservation reservation = (Reservation) em.createQuery("select distinct a from Reservation a where reservationId = 1L")
                .setHint("javax.persistence.loadgraph", graph)
                .getSingleResult();
        LOGGER.info("Reservation is {}", reservation);
        reservation.getPatients().forEach( e-> LOGGER.info("Patient is {}", e));
        reservation.getResources().forEach( e-> {
            LOGGER.info("resource is {}", e);
            LOGGER.info("hirarchy is {}", e.getHierarchy());
        });
    }
}
