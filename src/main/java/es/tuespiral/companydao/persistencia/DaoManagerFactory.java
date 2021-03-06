package es.tuespiral.companydao.persistencia;

import es.tuespiral.companydao.persistencia.dao.excepciones.DataAccessException;
import es.tuespiral.companydao.persistencia.implementacion.oracle.DaoManagerOracle;
import es.tuespiral.companydao.persistencia.dao.interfaces.DaoManager;

public class DaoManagerFactory {
    private static DaoManager daoManager;
    
    public static DaoManager getDaoManager() throws DataAccessException {
        if(daoManager == null) {
            daoManager = new DaoManagerOracle();
        }
        return daoManager;
    }
}
