package edu.lawrence.freecycle.Controllers;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String m) {
        super(m);
    }
}
