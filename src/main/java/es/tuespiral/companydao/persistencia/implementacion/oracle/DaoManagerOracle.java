package es.tuespiral.companydao.persistencia.implementacion.oracle;

import es.tuespiral.companydao.persistencia.dao.excepciones.DataAccessException;
import es.tuespiral.companydao.persistencia.dao.interfaces.DaoManager;
import es.tuespiral.companydao.persistencia.dao.interfaces.ProductCategoryDao;
import es.tuespiral.companydao.persistencia.dao.interfaces.ProductDao;
import es.tuespiral.companydao.utilidades.DBUtils;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoManagerOracle implements DaoManager {
    private Connection conn;
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;
   
    
    public DaoManagerOracle() throws DataAccessException {
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException ex) {
            throw new DataAccessException("Error al instanciar el DaoManagerOracle", ex);
        }
    }
    
    @Override
    public ProductCategoryDao getProductCategoryDao() {
        if (productCategoryDao == null) {
            productCategoryDao = new ProductCategoryDaoOracle(conn);
        }
        return productCategoryDao;
    }
    
    @Override
    public ProductDao getProductDao () {
        if (productDao == null) {
            productDao = new ProductDaoOracle(conn);
        }
        return productDao;
    }

    
    
}
