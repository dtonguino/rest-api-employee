package co.gestor.restapiemployee.exception;

public class EmployeeNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -6168650730998719643L;

    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}