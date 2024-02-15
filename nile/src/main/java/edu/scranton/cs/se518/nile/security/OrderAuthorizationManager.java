package edu.scranton.cs.se518.nile.security;

import edu.scranton.cs.se518.nile.models.User;

import edu.scranton.cs.se518.nile.repositories.UserRepository;
import edu.scranton.cs.se518.nile.services.OrderService;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.stereotype.Component;

import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.function.Supplier;

/**
 * AuthorizationManager for deciding whether a request should be allowed to access a given order.
 */
@Component
public class OrderAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    OrderService orderService;
    
    
    

    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        // TODO(student) Update this method to return a correct authorization decision.
        // You should not need to define what "correct" means here - OrderService already handles that.
    	
    	NilePrincipal nilePrincipal = (NilePrincipal) authentication.get().getPrincipal();
    	
    	if(authentication.get().getAuthorities().contains("ROLE_ADMINISTRATOR")|| authentication.get().getAuthorities().contains("VIEW_CUSTOMER_ORDER"))
    	return new AuthorizationDecision(true);
    	
    	else return new AuthorizationDecision(orderService.belongsToUser(nilePrincipal.getUser(), Long.parseLong(context.getVariables().get("id"))));
    	
    }
}
