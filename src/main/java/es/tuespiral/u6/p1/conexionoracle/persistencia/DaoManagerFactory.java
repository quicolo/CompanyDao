package es.tuespiral.u6.p1.conexionoracle.persistencia;

import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.excepciones.DataAccessException;
import es.tuespiral.u6.p1.conexionoracle.persistencia.implementacion.oracle.DaoManagerOracle;
import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.interfaces.DaoManager;

public class DaoManagerFactory {
    private static DaoManager daoManager;
    
    public static DaoManager getDaoManager() throws DataAccessException {
        if(daoManager == null) {
            daoManager = new DaoManagerOracle();
        }
        return daoManager;
    }
}
