package com.vinod.jpaexamples.transactionality;

import com.vinod.jpaexamples.commonentity.Course;
import com.vinod.jpaexamples.commonentity.Passport;
import com.vinod.jpaexamples.commonentity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class TransactionService {

    private Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    EntityManager em;

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodOne(){
        LOGGER.info("Invoke Method One");

        Course courseNew = new Course("History");
        em.persist(courseNew); // This pics the id and wait for flush because primary key generation strategy is sequence by default (if supports)
                               // If strategy is identity persist directly

        methodTwo();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodTwo(){
        LOGGER.info("Invoke Method Two");

        //Still Course will not flush
        Student student = (Student) em.createQuery("select s from Student s where id = 20001", Student.class).getSingleResult();

        //Here the course created in method one get flush before the select statement
        Course course = (Course) em.createQuery("select c from Course c where id = 10001L", Course.class).getSingleResult();
        course.setName("Maths2");
        em.flush(); // Makes entity manager flush changes immediatly

        methodThree(course);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodThree(Course c){
        c.setName("Maths222");
        LOGGER.info("Invoke Method Three");

        //The c object changes flush to the database at this point
        Course course = (Course) em.createQuery("select c from Course c where id = 10002L", Course.class).getSingleResult();
        course.setName("Science2");

        //Here since the exception is throw within try catch rollback are not take place
        //But if it is thrown here without handle using catch all changes above will rollback
        try{
            throw new IllegalArgumentException("exception throws by me");
        }catch (Exception e){
            LOGGER.error("{}", e.getMessage());
        }
    }
}
