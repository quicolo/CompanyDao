package es.tuespiral.companydao.negocio;

import es.tuespiral.companydao.persistencia.dao.excepciones.DataAccessException;
import es.tuespiral.companydao.persistencia.DaoManagerFactory;
import es.tuespiral.companydao.persistencia.dao.interfaces.DaoManager;
import es.tuespiral.companydao.persistencia.dao.interfaces.ProductCategoryDao;
import java.util.List;

public class ProductCategory {
    private Long categoryId;
    private String categoryName;

    public ProductCategory(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductCategory{" + "categoryId=" + categoryId + ", categoryName=" + categoryName + '}';
    }
    
    public static List<ProductCategory> consultarTodos() throws DataAccessException {
        DaoManager dm = DaoManagerFactory.getDaoManager();
        ProductCategoryDao dao = dm.getProductCategoryDao();
        return dao.obtenerTodos();
    }
    
    public static ProductCategory consultarPorId(Long id) throws DataAccessException {
        DaoManager dm = DaoManagerFactory.getDaoManager();
        ProductCategoryDao dao = dm.getProductCategoryDao();
        return dao.obtener(id);
    }
    
    public void guardar() throws DataAccessException {
        DaoManager dm = DaoManagerFactory.getDaoManager();
        ProductCategoryDao dao = dm.getProductCategoryDao();
        
        if (categoryId == null) {
            dao.insertar(this);
        } else {
            dao.actualizar(this);
        }
    }
    
    public void eliminar() throws DataAccessException {
        DaoManager dm = DaoManagerFactory.getDaoManager();
        ProductCategoryDao dao = dm.getProductCategoryDao();
        dao.eliminar(this);
    }
}
