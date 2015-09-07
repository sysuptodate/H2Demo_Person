/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.uptodate.h2demo_person;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import sys.uptodate.h2demo_person.db.Person;
import sys.uptodate.h2demo_person.db.PersonJpaController;
import sys.uptodate.h2demo_person.db.exceptions.NonexistentEntityException;

/**
 *
 * @author Cam
 */
public class Main {

    private static final PersonJpaController personDb;

    static {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("personPU");
        EntityManager em = emf.createEntityManager();
        personDb = new PersonJpaController(emf);
    }

    public static void main(String[] args) {
        System.out.println("Number of persons: " + personDb.getPersonCount());
        
        Person john = new Person();
        john.setId(1);
        john.setName("John");
        
        try {
            personDb.create(john);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Number of persons after create: " + personDb.getPersonCount());
        
        try {
            personDb.destroy(john.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Number of persons after destroy: " + personDb.getPersonCount());
    }
}
