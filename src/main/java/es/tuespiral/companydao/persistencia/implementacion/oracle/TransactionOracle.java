package es.tuespiral.companydao.persistencia.implementacion.oracle;

import es.tuespiral.companydao.persistencia.dao.excepciones.TransactionException;
import es.tuespiral.companydao.persistencia.dao.interfaces.TransactionInterface;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class TransactionOracle implements TransactionInterface {

    protected Connection conn;

    public TransactionOracle(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void beginTransaction() throws TransactionException {
        try {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            } else {
                throw new TransactionException("Ya hay una transacción empezada. Debe cerrarse primero");
            }
        } catch (SQLException ex) {
            throw new TransactionException("Fallo al comenzar la transacción", ex);
        }
    }

    @Override
    public void endTransaction() throws TransactionException {
        try {
            if (!conn.getAutoCommit()) {
                conn.setAutoCommit(true);
            } else {
                throw new TransactionException("No hay ninguna transacción empezada. No se puede cerrar");
            }
        } catch (SQLException ex) {
            throw new TransactionException("Fallo al finalizar la transacción", ex);
        }
    }

    @Override
    public void commit() throws TransactionException {
        try {
            conn.commit();
        } catch (SQLException ex) {
            throw new TransactionException("Fallo al aplicar los cambios (commit)", ex);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            throw new TransactionException("Fallo al deshacer los cambios (rollback)", ex);
        }
    }
}
