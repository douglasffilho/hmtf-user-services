package br.com.douglasffilho.UserServices.rest.api.v1;

import br.com.douglasffilho.UserServices.rest.api.publicEndpoints.PublicApiV1Endpoints;
import br.com.douglasffilho.UserServices.security.utils.JwtTokenUtils;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(PublicApiV1Endpoints.PUBLIC_API_V1_AUTH_ROOT_ENDPOINT)
@SwaggerDefinition(
        info = @Info(
                title = "AuthenticationApi",
                description = "API para autenticação de usuário",
                version = "v1"
        )
)
public class AuthenticationApi {

    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    //TODO continuar endpoint de autenticação

}
