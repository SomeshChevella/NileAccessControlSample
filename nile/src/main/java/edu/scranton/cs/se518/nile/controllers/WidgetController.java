package edu.scranton.cs.se518.nile.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class WidgetController {

    /**
     * If it actually did anything, this would return the widgets for sale.
     * Everyone should be able to access this endpoint, authenticated or not.
     */
    @GetMapping("/widgets")
    @ResponseStatus(value = HttpStatus.OK)
    public void list() {
        // This is intentionally a stub implementation.
    }

    /**
     * If it actually did anything, this would return a single, specific widget for sale.
     * Everyone should be able to access this endpoint, authenticated or not.
     */
    @GetMapping("/widgets/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void get() {
        // This is intentionally a stub implementation.
    }

    /**
     * If it actually did anything, this would list a new widget for sale on the platform.
     * In order to list a widget for sale a user must be authenticated and possess the LIST_WIDGET authority.
     */
    @PostMapping("/widgets")
    @ResponseStatus(value = HttpStatus.OK)
    public void post() {
        // This is intentionally a stub implementation.
    }

    /**
     * If it actually did anything, this would delete a widget from the system.
     * Nile loves making lots and lots of money, so this should only happen if some terms of service are violated.
     * As a result, widgets can only be deleted by an authenticated user with the ADMINISTRATOR role
     * (equivalently known as the ROLE_ADMINISTRATOR authority).
     */
    @DeleteMapping("/widgets/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        // This is intentionally a stub implementation.
    }
}
