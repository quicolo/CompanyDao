/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tuespiral.u6.p1.conexionoracle.persistencia.dao.interfaces;

import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.interfaces.Dao;
import es.tuespiral.u6.p1.conexionoracle.negocio.Product;

/**
 *
 * @author Kike
 */
public interface ProductDao extends Dao<Product, Long>{
    // Aquí no hay que hacer nada. 
    // Solo heredamos los métodos a implementar pero ya habiendo 
    // concretado los tipos genéricos.
}
