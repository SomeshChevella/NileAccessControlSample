package edu.scranton.cs.se518.nile.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    /**
     * If it actually did anything, this would return the details of a specific order.
     * This is the most interesting operations from an authorization perspective.
     * We obviously don't want everybody seeing everyone else's orders (confidentiality, anyone?)
     * so a regular user should only be allowed to access the orders that belong to them.
     * What exactly "belong to" means here is defined in the OrderService.java file -
     * you will need to call this method from the code you add.
     * </p>
     * Occasionally, though, some Nile employees may need to access user orders.
     * As a result, a second case you'll need to account for is that authenticated users with
     * the VIEW_CUSTOMER_ORDER authority should be allowed to view any order,
     * even if it doesn't belong to them.
     */
    @GetMapping("/orders/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void get() {
        // This is intentionally a stub implementation.
    }
    /**
     * If it actually did anything, this would create a new order.
     * Any authenticated user should be able to create a new order.
     */
    @PostMapping("/orders")
    @ResponseStatus(value = HttpStatus.OK)
    public void post() {
        // This is intentionally a stub implementation.
    }

    /**
     * If it actually did anything, this would delete an order from the system.
     * Deleting orders is a pretty serious thing, restricting this operation to only administrators
     * and the rare user with the CANCEL_CUSTOMER_ORDER authority.
     */
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        // This is intentionally a stub implementation.
    }

}
