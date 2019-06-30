package com.vinod.jpaexamples.transactionality;

import com.vinod.jpaexamples.commonentity.Course;
import com.vinod.jpaexamples.commonentity.Passport;
import com.vinod.jpaexamples.commonentity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class TransactionService {

    @Autowired
    EntityManager em;

    private Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodOne(){
        LOGGER.info("Invoke Method One");
        Course courseNew = new Course("History");
        em.persist(courseNew);
        methodTwo();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodTwo(){
        LOGGER.info("Invoke Method Two");
        Student student = (Student) em.createQuery("select s from Student s where id = 20001", Student.class).getSingleResult();
        Course course = (Course) em.createQuery("select c from Course c where id = 10001L", Course.class).getSingleResult();
        course.setName("Maths2");
        em.flush();
        methodThree(course);
        Passport passport = (Passport) em.createQuery("select p from Passport p where id = 40001", Passport.class).getSingleResult();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void methodThree(Course c){
        c.setName("Maths222");
        LOGGER.info("Invoke Method Three");
        Course course = (Course) em.createQuery("select c from Course c where id = 10002L", Course.class).getSingleResult();
        course.setName("Science2");
        try{
            throw new IllegalArgumentException("exception throws by me");
        }catch (Exception e){
            LOGGER.error("{}", e.getMessage());
        }
    }
}
