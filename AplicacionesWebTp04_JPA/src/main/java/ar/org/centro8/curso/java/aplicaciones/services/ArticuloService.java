package ar.org.centro8.curso.java.aplicaciones.services;

import ar.org.centro8.curso.java.aplicaciones.entities.Articulo;
import ar.org.centro8.curso.java.aplicaciones.enums.Temporada;
import ar.org.centro8.curso.java.aplicaciones.enums.Tipo;
import ar.org.centro8.curso.java.aplicaciones.repositories.interfaces.I_ArticuloRepository;
import ar.org.centro8.curso.java.aplicaciones.repositories.jpa.ArticuloRepository;
//import ar.org.centro8.curso.java.aplicaciones.repositories.jdbc.ArticuloRepository;
//import ar.org.centro8.curso.java.connectors.Connector;
import com.google.gson.Gson;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/articulos/v1")
public class ArticuloService{
    
   // private I_ArticuloRepository ar=new ArticuloRepository(Connector.getConnection());
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
    private I_ArticuloRepository ar = new ArticuloRepository(emf);
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String info(){
        return "Servicio de articulos v1 activo.";
    }
    
    @GET
    @Path("/save")
    @Produces(MediaType.TEXT_PLAIN)
    public String save( 
                        @QueryParam("descripcion")String descripcion, 
                        @QueryParam("tipo")Tipo tipo, 
                        @QueryParam("color")String color, 
                        @QueryParam("talle_num")String talle_num, 
                        @QueryParam("stock")int stock, 
                        @QueryParam("stockMin")int stockMin, 
                        @QueryParam("stockMax")int stockMax, 
                        @QueryParam("costo")double costo, 
                        @QueryParam("precio")double precio, 
                        @QueryParam("temporada")Temporada temporada
    ){
        Articulo articulo=new Articulo(descripcion, tipo, color, talle_num, stock, stockMin, stockMax, costo, precio, temporada);
       
        ar.save(articulo);
        return articulo.getId()+"";
    }
    
    @GET
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public String remove(@QueryParam("id")int id){
        try {
            ar.remove(ar.getById(id));
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll(){
        return new Gson().toJson(ar.getAll());
    }
    
    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@QueryParam("id")int id){
        return new Gson().toJson(ar.getById(id));
    }
    
    @GET
    @Path("/likeDescripcion")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikeDescripcion(@QueryParam("descripcion")String descripcion){
        return new Gson().toJson(ar.getLikeDescripcion(descripcion));
    }
    
}