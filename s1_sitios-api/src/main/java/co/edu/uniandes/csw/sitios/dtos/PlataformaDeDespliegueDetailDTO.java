/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.SitioWebEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.ballesteros
 */
public class PlataformaDeDespliegueDetailDTO extends PlataformaDeDespliegueDTO implements Serializable{
    
/*
* La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
* plataforma de Despliegue    
*/
private List<SitioWebDTO> sitiosWeb;

/*
* La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
* plataforma de Despliegue    
*/
private Long id;

/*
* Constructor vacio de plataformaDeDespliegueDetailDTO
*/
public PlataformaDeDespliegueDetailDTO(){
   super();
}

/*
* Constructor vacio de plataformaDeDespliegueDetailDTO
*/
public PlataformaDeDespliegueDetailDTO(PlataformaDeDespliegueEntity plataformaEntity){
   super(plataformaEntity);
       if(plataformaEntity.getSitiosWeb()!= null && plataformaEntity != null){
           sitiosWeb = new ArrayList<>();
           for(SitioWebEntity entitySitio : plataformaEntity.getSitiosWeb()){
               sitiosWeb.add(new SitioWebDTO(entitySitio));
           }
       }
}

    /**
     * @return the sitiosWeb
     */
    public List<SitioWebDTO> getSitiosWeb() {
        return sitiosWeb;
    }

    /**
     * @param sitiosWeb the sitiosWeb to set
     */
    public void setSitiosWeb(List<SitioWebDTO> sitiosWeb) {
        this.sitiosWeb = sitiosWeb;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}
