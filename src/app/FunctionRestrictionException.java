package app;

public class FunctionRestrictionException extends RuntimeException {
	/**
	 * This is the wrapper to Throw custom Exceptions to use in developed try / catches
	 * regarding what can happen in the logic fails and map error occurs in the code development.
	 */

	    private static final long serialVersionUID = 6672888958102461860L;

	    public FunctionRestrictionException() {
	        super();
	    }

	    public FunctionRestrictionException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public FunctionRestrictionException(String message) {
	        super(message);
	    }

	    public FunctionRestrictionException(Throwable cause) {
	        super(cause);
	    }
}
