/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tuespiral.companydao.persistencia.dao.interfaces;

import es.tuespiral.companydao.persistencia.dao.interfaces.Dao;
import es.tuespiral.companydao.negocio.Product;

/**
 *
 * @author Kike
 */
public interface ProductDao extends Dao<Product, Long>{
    // Aquí no hay que hacer nada. 
    // Solo heredamos los métodos a implementar pero ya habiendo 
    // concretado los tipos genéricos.
}
