package com.quickleap_assignment.api_gateway.filters;

import com.quickleap_assignment.api_gateway.config.RouteValidator;
import com.quickleap_assignment.api_gateway.config.exceptions.InvalidTokenException;
import com.quickleap_assignment.api_gateway.config.exceptions.NotLoggedInException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    public static final String AUTHENTICATE_ENDPOINT = "http://localhost:8005/api/user/auth/authenticate";
    private final RouteValidator routeValidator;
    private final RestTemplate restTemplate;

    public AuthenticationFilter(RouteValidator routeValidator, RestTemplate restTemplate) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.restTemplate = restTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {


            try{
                ServerHttpRequest request = null;
                if(routeValidator.isSecured.test(exchange.getRequest())) {

                    if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                        throw new NotLoggedInException(HttpStatus.UNAUTHORIZED);
                    }
                    String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);


                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                        authHeader = authHeader.substring(7);
                    }
                    else{
                        throw new InvalidTokenException(HttpStatus.BAD_REQUEST);
                    }
                    try {
              // REST call to AUTH service

                        HttpHeaders headers = new HttpHeaders();
                        headers.set("Authorization", "Bearer "+authHeader);
                        HttpEntity<String> entity = new HttpEntity<>(headers);
                        ResponseEntity<Map> response = restTemplate.exchange(AUTHENTICATE_ENDPOINT,
                                HttpMethod.GET,
                                entity,
                                Map.class);

                        String userId = (String) response.getBody().get("id");
                        request= exchange.getRequest().mutate().header("X-USER-ID", userId).build();
                        exchange = exchange.mutate().request(request).build();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new InvalidTokenException(HttpStatus.UNAUTHORIZED);
                    }
                }
                return chain.filter(exchange);
            }
            catch(Exception e){
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }));
    }

    public static class Config {}
}
