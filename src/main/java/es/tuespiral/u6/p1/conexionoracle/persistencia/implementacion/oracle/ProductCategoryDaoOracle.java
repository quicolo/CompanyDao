package es.tuespiral.u6.p1.conexionoracle.persistencia.implementacion.oracle;

import es.tuespiral.u6.p1.conexionoracle.negocio.ProductCategory;
import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.excepciones.DataAccessException;
import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.excepciones.NotFoundException;
import es.tuespiral.u6.p1.conexionoracle.persistencia.dao.interfaces.ProductCategoryDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoOracle extends TransactionOracle implements ProductCategoryDao {

    private final String nombreClase;
    private final String nombreTabla = "PRODUCT_CATEGORIES";
    private final String SELECT_ALL = "SELECT * FROM " + nombreTabla;
    private final String SELECT_ID = "SELECT * FROM " + nombreTabla + " WHERE CATEGORY_ID = ?";
    private final String SELECT_ROW_ID = "SELECT * FROM " + nombreTabla + " WHERE ROWID = ?";
    private final String INSERT = "INSERT INTO " + nombreTabla + " (CATEGORY_NAME) VALUES (?)";
    private final String UPDATE = "UPDATE " + nombreTabla + " SET CATEGORY_NAME = ? WHERE CATEGORY_ID = ?";
    private final String DELETE = "DELETE FROM " + nombreTabla + " WHERE CATEGORY_ID = ?";

    public ProductCategoryDaoOracle(Connection conn) {
        super(conn);
        nombreClase = this.getClass().getName();
    }

    @Override
    public void insertar(ProductCategory obj) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getCategoryName());
            int numFilas = st.executeUpdate();
            if (numFilas != 1) {
                throw new DataAccessException(nombreClase + " falló al insertar en la BD");
            }

            // Recuperamos la clave primaria generada para copiarla en el objeto    
            ResultSet rs = st.getGeneratedKeys();
            if (rs != null && rs.next()) {
                RowId rowId = rs.getRowId(1);
                st = conn.prepareStatement(SELECT_ROW_ID);
                st.setRowId(1, rowId);
                ResultSet rsClave = st.executeQuery();
                if (rsClave.next()) {
                    Long id = rsClave.getLong(1);
                    obj.setCategoryId(id);
                }
            }

        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al insertar en la BD", ex);
        }
    }

    @Override
    public void actualizar(ProductCategory obj) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, obj.getCategoryName());
            st.setLong(2, obj.getCategoryId());
            int numFilas = st.executeUpdate();
            if (numFilas != 1) {
                throw new DataAccessException(nombreClase + " falló al actualizar en la BD");
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al actualizar en la BD", ex);
        }
    }

    @Override
    public void eliminar(ProductCategory obj) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(DELETE);
            st.setLong(1, obj.getCategoryId());
            int numFilas = st.executeUpdate();
            if (numFilas != 1) {
                throw new DataAccessException(nombreClase + " falló al borrar en la BD");
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al borrar en la BD", ex);
        }
    }

    @Override
    public List<ProductCategory> obtenerTodos() throws DataAccessException {
        List<ProductCategory> lista = new ArrayList<>();

        try {
            PreparedStatement st = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductCategory category = extractFrom(rs);
                lista.add(category);
            }
            return lista;
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al seleccionar de la BD", ex);
        }
    }

    @Override
    public ProductCategory obtener(Long id) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(SELECT_ID);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs != null && rs.next()) {
                return extractFrom(rs);
            } else {
                throw new NotFoundException(nombreClase + " falló al seleccionar el id = " + id);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al seleccionar el id = " + id, ex);
        }
    }

    private ProductCategory extractFrom(ResultSet rs) throws SQLException {
        Long id = rs.getLong(1);
        String name = rs.getString(2);
        return new ProductCategory(id, name);
    }
}
