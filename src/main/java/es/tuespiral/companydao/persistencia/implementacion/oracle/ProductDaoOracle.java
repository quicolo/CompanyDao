package es.tuespiral.companydao.persistencia.implementacion.oracle;

import es.tuespiral.companydao.negocio.Product;
import es.tuespiral.companydao.persistencia.dao.excepciones.DataAccessException;
import es.tuespiral.companydao.persistencia.dao.excepciones.NotFoundException;
import es.tuespiral.companydao.persistencia.dao.interfaces.ProductDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoOracle extends TransactionOracle implements ProductDao {
    private final String nombreClase;
    private final String nombreTabla = "PRODUCTS";
    private final String SELECT_ALL = "SELECT * FROM " + nombreTabla;
    private final String SELECT_ID = "SELECT * FROM " + nombreTabla + " WHERE PRODUCT_ID = ?";
    private final String SELECT_ROW_ID = "SELECT * FROM " + nombreTabla + " WHERE ROWID = ?";
    private final String INSERT = "INSERT INTO " + nombreTabla 
            + "(PRODUCT_NAME, DESCRIPTION, STANDARD_COST, LIST_PRICE, CATEGORY_ID) "
            + "VALUES (?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE " + nombreTabla + " SET "
            + "PRODUCT_NAME = ?, DESCRIPTION = ?, STANDARD_COST = ?, LIST_PRICE = ?, CATEGORY_ID = ?"
            + " WHERE PRODUCT_ID = ?";
    private final String DELETE = "DELETE FROM " + nombreTabla + " WHERE PRODUCT_ID = ?";

    public ProductDaoOracle(Connection conn) {
        super(conn);
        nombreClase = this.getClass().getName();
    }
    
    @Override
    public void insertar(Product obj) throws DataAccessException {
         try {
            PreparedStatement st = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getProductName());
            st.setString(2, obj.getDescription());
            st.setDouble(3, obj.getStandardCost());
            st.setDouble(4, obj.getListPrice());
            st.setLong(5, obj.getCategoryId());
            
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
                    obj.setProductId(id);
                }
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al insertar en la BD", ex);
        }
    }

    @Override
    public void actualizar(Product obj) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(UPDATE);
            st.setString(1, obj.getProductName());
            st.setString(2, obj.getDescription());
            st.setDouble(3, obj.getStandardCost());
            st.setDouble(4, obj.getListPrice());
            st.setLong(5, obj.getCategoryId());
            st.setLong(6, obj.getProductId());
            
            int numFilas = st.executeUpdate();
            if (numFilas != 1) {
                throw new DataAccessException(nombreClase + " falló al actualizar en la BD");
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al actualizar en la BD", ex);
        }
    }

    @Override
    public void eliminar(Product obj) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(DELETE);
            st.setLong(1, obj.getProductId());
            
            int numFilas = st.executeUpdate();
            if (numFilas != 1) {
                throw new DataAccessException(nombreClase + " falló al borrar en la BD");
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al borrar en la BD", ex);
        }
    }

    @Override
    public List<Product> obtenerTodos() throws DataAccessException {
        List<Product> lista = new ArrayList<>();
                
        try {
            PreparedStatement st = conn.prepareStatement(SELECT_ALL);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Product category = extractFrom(rs);
                lista.add(category);
            }
            return lista;
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al seleccionar de la BD", ex);
        }
    }

    @Override
    public Product obtener(Long id) throws DataAccessException {
        try {
            PreparedStatement st = conn.prepareStatement(SELECT_ID);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if(rs != null && rs.next()) {
                return extractFrom(rs);
            } else {
                throw new NotFoundException(nombreClase + " falló al seleccionar el id = "+id);
            }
        } catch (SQLException ex) {
            throw new DataAccessException(nombreClase + " falló al seleccionar el id = "+id, ex);
        }
    }

    private Product extractFrom(ResultSet rs) throws SQLException {
        Long id = rs.getLong(1);
        String name = rs.getString(2);
        String descripcion = rs.getString(3);
        Double standardCost = rs.getDouble(4);
        Double listPrice = rs.getDouble(5);
        Long idCategory = rs.getLong(6);
        return new Product(id, name, descripcion, standardCost, listPrice, idCategory);
    }
    
}
