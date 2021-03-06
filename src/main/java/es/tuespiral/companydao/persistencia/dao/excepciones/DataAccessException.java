package es.tuespiral.companydao.persistencia.dao.excepciones;

public class DataAccessException extends Exception {

    public DataAccessException() {
    }

    public DataAccessException(String msg) {
        super(msg);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
    
    
}
