package ar.org.centro8.curso.java.aplicaciones.test;
import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jpa.ArticuloRepository;
//import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
//import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
//import ar.org.centro8.curso.java.connectors.Connector;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestArticuloRepository {
    public static void main(String[] args) {
        //I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
        I_ArticuloRepository ar = new ArticuloRepository(emf);
        
        //ar.save(null);
        System.out.println("***************** SAVE**************************");
        ar.save(new Articulo( "Bota de Cuero", Tipo.CALZADO, "Rojo", "36", 0, 0, 0, 0d, 0d, Temporada.VERANO));
        ar.save(new Articulo("Remera", Tipo.ROPA, "Rojo", "L", 0, 0, 0, 0d, 0d, Temporada.VERANO));
        ar.save(new Articulo("Blusa", Tipo.ROPA, "Rojo", "M", 0, 0, 0, 0d, 0d, Temporada.VERANO));
        ar.save(new Articulo("Sandalia", Tipo.CALZADO, "Rojo", "36", 0, 0, 0, 0d, 0d, Temporada.VERANO));
       
        int id = 30;
        System.out.println("***************** REMOVE id = "+id+"**************************");
        Articulo articulo=ar.getById(id);
        if(articulo!=null && articulo.getId()!=null && articulo.getId()!=0)
            ar.remove(articulo);
        id=28;
        System.out.println("***************** UPDATE id = "+id+" Color=Negro**************************");
        articulo=ar.getById(id);
        if(articulo!=null && articulo.getId()!=null && articulo.getId()!=0){
            articulo.setColor("Negro");
            ar.update(articulo);
        }
        
        ar.getAll().forEach(System.out::println);
        System.out.println("***************** DESCRIPCION: Contiene bl**************************");
        ar.getLikeDescripcion("bl").forEach(System.out::println);
        emf.close();
    }
}