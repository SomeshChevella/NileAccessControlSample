package edu.scranton.cs.se518.nile.services;

import edu.scranton.cs.se518.nile.models.User;
import edu.scranton.cs.se518.nile.security.NilePrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class OrderService {

    /**
     * Check whether the provided order ID "belongs to" the current user.
     * This notion of "belongs to" doesn't really matter for this project.
     * In a real application this would likely rely on actual data stored in
     * a database that associates an order with the ordering user. Since that
     * part of the business logic doesn't matter for our purposes here we use
     * a far dumber strategy based on integer equality.
     *
     * @param user  The Nile user for whom we want to check order ownership.
     * @param orderId  The ID of the order that may or may not belong to the current user.
     * @return  True if the order belongs to the current user. False otherwise.
     */
    public boolean belongsToUser(User user, long orderId) {
        return orderId == user.getId();
    }
}
