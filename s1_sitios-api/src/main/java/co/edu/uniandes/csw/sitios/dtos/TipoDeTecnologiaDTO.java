/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.dtos;

/**
 *
 * @author estudiante
 */
public class TipoDeTecnologiaDTO {
    
    /*
    * Technology's name
    */
    private String nombre;
    /*
    *Category of the technology
    */
    private String categoria;

    /**
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the name to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the category
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the category to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
