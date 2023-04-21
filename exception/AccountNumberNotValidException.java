package vn.funix.FX18420.java.asm04.exception;

public class AccountNumberNotValidException extends Exception{

    /**
     * Constructs an <code>InterruptedException</code> with no detail  message.
     */
    public AccountNumberNotValidException() {
        super();
    }

    /**
     * Constructs an <code>InterruptedException</code> with the
     * specified detail message.
     *
     * @param   s   the detail message.
     */
    public AccountNumberNotValidException(String s) {
        super(s);
    }
}
