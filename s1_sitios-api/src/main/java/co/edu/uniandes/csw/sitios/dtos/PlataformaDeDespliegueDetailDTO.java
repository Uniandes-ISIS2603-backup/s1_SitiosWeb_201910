/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.io.Serializable;
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
* Constructor vacio de plataformaDeDespliegueDetailDTO
*/
public PlataformaDeDespliegueDetailDTO(){
    
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
}
