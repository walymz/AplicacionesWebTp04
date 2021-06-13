
package ar.org.centro8.curso.java.aplicaciones.test;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAPU");
        EntityManager em = emf.createEntityManager();    
        System.out.println("****************** SAVE ******************** ");
        Articulo articulo;
        articulo = new Articulo("Campera");
//        em.getTransaction().begin();
//        em.persist(articulo);
//        em.getTransaction().commit();
//        System.out.println(articulo);
        System.out.println("****************** ALL ******************** ");
        em.createNamedQuery("Articulo.findAll").getResultList().forEach(System.out::println);
        
        System.out.println("****************** POR DESCRIPCIÓN = CAmpera ******************** ");
        Query query = em.createNamedQuery("Articulo.findByDescripcion");
              query.setParameter("descripcion", "CAmpera");  //keysense
              query.getResultList().forEach(System.out::println);

        System.out.println("****************** REMOVE id = 133 ******************** ");      
        
        query = em.createNamedQuery("Articulo.findById");
        query.setParameter("id", 4);
        articulo = (Articulo)query.getSingleResult();
        if(articulo!=null){
            em.getTransaction().begin();
            em.remove(articulo);
            em.getTransaction().commit();
        }
        
        System.out.println("****************** UPDATE id = 5 ******************** ");      
        
        query = em.createNamedQuery("Articulo.findById");
        query.setParameter("id", 5);
        articulo = (Articulo)query.getSingleResult();
        if(articulo!=null){
            articulo.setDescripcion("Plato de ceramica");
            em.getTransaction().begin();
            em.persist(articulo);
            em.getTransaction().commit();
        }
        
        em.close();
        emf.close();
    }
}
