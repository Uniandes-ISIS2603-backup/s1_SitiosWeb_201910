/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.sitios.ejb;

import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity;
import co.edu.uniandes.csw.sitios.entities.PlataformaDeDespliegueEntity.TipoHosting;
import co.edu.uniandes.csw.sitios.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.sitios.persistence.PlataformaDeDesplieguePersistence;
import java.lang.annotation.Target;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.ballesteros
 */

@Stateless
public class PlataformaDeDespliegueLogic {
    
    @Inject
    private PlataformaDeDesplieguePersistence persistence;
    
    // El metodo recibe editorial entity porque back no conoce a los DTOS
    public PlataformaDeDespliegueEntity createPlataformaDeDespliegue(PlataformaDeDespliegueEntity plataforma) throws BusinessLogicException{
        
         ///////                    Reglas De Negocio                  ////////
        
        // ip = cumplir con los rangos de ips determinado el tipo de sistema Paas, Iaas, Saas, OnPremise
        
        
        String ip = plataforma.getIp();
        String[] ips = ip.split(".");
        //ip = no puede ser null
        if(ip==null){
            throw new BusinessLogicException("La secuencia es nula");
        }
        //ip = no puede ser vacío
        if(ip.equals("")){
            throw new BusinessLogicException("La secuencia es vacia");
        }
        
        //ip = cumplir con la estructura #.#.#.# 
       
       /*if(ip.codePointAt(3)!=('.')||ip.codePointAt(7)!=('.')||ip.codePointAt(11)!=('.')){
         //   throw new BusinessLogicException("No hay congruencia en la cantidad de puntos que separan una ip: #.#.#.#");
       
        //}
        //ip = el numero minimo es 0.0.0.0 y el máximo es 255.255.255.255
        if(ips.length!=3){
            throw new BusinessLogicException("La secuencia esta incompleta");
        }
        try {
            Integer int1 = Integer.parseInt(ips[0]);
            Integer int2 = Integer.parseInt(ips[1]);
            Integer int3 = Integer.parseInt(ips[2]);
    } catch (NumberFormatException | NullPointerException nfe) {
        throw new BusinessLogicException("La secuencia esta incompleta");
    }
        //ip = la longitud del numero no debe ser mayor a 12
        try{
            char int1 = ip.charAt(15);
        }
        catch(IndexOutOfBoundsException ie){
            throw new BusinessLogicException("La secuencia supera el numero de enteros permitidos");
        }
        */
       /*
        //cpu = no puede ser nulo
        String cpu = plataforma.getCPU();
        if(cpu==null){
            throw new BusinessLogicException("La cpu es nula");
        }
        //cpu = no puede ser vacío
        if(cpu.equals("")){
            throw new BusinessLogicException("La cpu es vacia");
        }
        
        //clock = mayor a 0
        String clock = plataforma.getClock();
        String[] unidades = {"Hz","Hertz","KHz", "Kilo Hertz", "Mega Hertz", "GigaHertz", "Tera Hertz","Peta Hertz", "Hexa Hertz", "Zetta Hertz", "Yotta Hertz", "MHz", "THz","GHz","PHz","HHz","ZHz"};
     
            Boolean encontrado = false;
            String str2 =null;
            String str3 =null;
            double actual = 0;
            for(int i = 0;i<clock.length()&&(encontrado==false);i++){
               char caracter = clock.charAt(i);
            String str1 = new StringBuilder().append(caracter).toString();
            try{
                    actual = Double.parseDouble(str1);
                    str2 += str1;
            }
            catch(NumberFormatException | NullPointerException nfe){
              str3 += str1;  
            }
            
            }
            if(actual<=0){
                throw new BusinessLogicException("El numero de clock es incorrecto");
            }
            for(int i = 0; i<unidades.length&&encontrado==false;i++){
               if(clock.endsWith(unidades[i])){
                   encontrado = true;
               } 
               else if((i++)==(unidades.length)){
                throw new BusinessLogicException("Las unidades no son adecuadas");
            }
            }
            
        
        //clock = uso de unidades(GHz,MHz, etc)
        */
        
        
        TipoHosting host = plataforma.getHosting();
        if(host!=null){
        
        //hosting = hosting contenido en las enumeraciones
        if(!host.equals(TipoHosting.IaaS)&&!host.equals(TipoHosting.OnPremise)&&!host.equals(TipoHosting.PaaS)&&!host.equals(TipoHosting.SaaS)){
            throw new BusinessLogicException("No pertenece a los tipo hosting permitidos");
        }
        //hosting = no puede ser nulo
        if(host==null){
            throw new BusinessLogicException("El hosting no puede ser nulo");
        }
        //hosting = no puede ser vacío
        if(host.equals("")){
            throw new BusinessLogicException("El hosting no puede ser vacio");
        }
        //sitiosWeb = no puede ser null
        
        //Invoco a la persistencia para crear a la plataforma
        }
        persistence.create(plataforma);
        return plataforma;
        }
        
}

