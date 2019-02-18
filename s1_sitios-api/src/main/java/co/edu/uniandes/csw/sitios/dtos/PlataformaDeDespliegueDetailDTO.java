/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

import java.util.ArrayList;

/**
 *
 * @author s.ballesteros
 */
public class PlataformaDeDespliegueDetailDTO extends PlataformaDeDespliegueDTO {
    
/*
* La lista de sitiosWeb indica cuales sitios web pertenecen a una unica 
* plataforma de Despliegue    
*/
private ArrayList<SitioWebDTO> sitiosWeb;

/*
* Constructor vacio de plataformaDeDespliegueDetailDTO
*/
public PlataformaDeDespliegueDetailDTO(){
    
}
}
