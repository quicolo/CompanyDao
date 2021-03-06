
package es.tuespiral.companydao.persistencia.dao.excepciones;

public class TransactionException extends DataAccessException {

    public TransactionException() {
    }

    public TransactionException(String msg) {
        super(msg);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionException(Throwable cause) {
        super(cause);
    }
    
    
}
