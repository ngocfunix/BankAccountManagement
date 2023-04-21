package vn.funix.FX18420.java.asm04.exception;

public class CustomerIdNotValidException extends Exception{

    /**
     * Constructs an <code>InterruptedException</code> with no detail  message.
     */
    public CustomerIdNotValidException() {
        super();
    }

    /**
     * Constructs an <code>InterruptedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public CustomerIdNotValidException(String s) {
        super(s);
    }
}
