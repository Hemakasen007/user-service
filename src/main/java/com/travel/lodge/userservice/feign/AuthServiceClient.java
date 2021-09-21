package com.travel.lodge.userservice.feign;

import com.travel.lodge.userservice.dto.KeycloakUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "authClient", url="${services.auth.base}")
public interface AuthServiceClient {

    @PostMapping("${services.auth.add-user}")
    ResponseEntity<Object> createUser(KeycloakUser user);
}
