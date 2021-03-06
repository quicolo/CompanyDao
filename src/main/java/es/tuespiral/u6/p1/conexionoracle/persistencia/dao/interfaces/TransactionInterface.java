package es.tuespiral.u6.p1.conexionoracle.persistencia.dao.interfaces;

import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.excepciones.DataAccessException;

public interface TransactionInterface {
        
    void beginTransaction() throws DataAccessException;
    
    void endTransaction()  throws DataAccessException;
    
    void commit() throws DataAccessException;
    
    void rollback() throws DataAccessException;
}
