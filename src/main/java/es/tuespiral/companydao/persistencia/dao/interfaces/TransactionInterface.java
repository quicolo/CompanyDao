package es.tuespiral.companydao.persistencia.dao.interfaces;

import es.tuespiral.companydao.persistencia.dao.excepciones.DataAccessException;

public interface TransactionInterface {
        
    void beginTransaction() throws DataAccessException;
    
    void endTransaction()  throws DataAccessException;
    
    void commit() throws DataAccessException;
    
    void rollback() throws DataAccessException;
}
