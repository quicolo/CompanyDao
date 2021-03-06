/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.tuespiral.u6.p1.conexionoracle.persistencia.dao.interfaces;

import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.excepciones.DataAccessException;
import java.util.List;

/**
 * Interfaz común a todos los DAO. Se usan dos genéricos: 
 * T: para el tipo de datos
 * K: para la clave que lo identifica
*/
public interface Dao <T, K> extends TransactionInterface {
    void insertar(T obj) throws DataAccessException;
    
    void actualizar(T obj) throws DataAccessException;
    
    void eliminar(T obj) throws DataAccessException;
    
    List<T> obtenerTodos() throws DataAccessException;
    
    T obtener(K id)  throws DataAccessException;

}
