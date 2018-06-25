package br.com.douglasffilho.UserServices.rest.api.v1;

import br.com.douglasffilho.UserServices.rest.api.publicEndpoints.PublicApiV1Endpoints;
import br.com.douglasffilho.UserServices.security.dto.JwtAuthenticationDTO;
import br.com.douglasffilho.UserServices.security.dto.TokenDTO;
import br.com.douglasffilho.UserServices.security.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(PublicApiV1Endpoints.PUBLIC_API_V1_AUTH_ROOT_ENDPOINT)
@Api(value = "AuthenticationApi", description = "API para autenticar usuários.")
public class AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "Endpoint de autenticação")
    public TokenDTO authorize(@Valid @RequestBody JwtAuthenticationDTO authenticationDTO, BindingResult result, HttpServletResponse response) throws IOException {
        log.info("M=AuthenticationApi.authorize, I=tentando autorizar login e gerar token para usuario {}", authenticationDTO.getEmail());

        if(result.hasErrors()) {
            log.error("M=AuthenticationApi.authorize, E=Erro ao tentar autorizar login: {}", result.getAllErrors().get(0).getDefaultMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, result.getAllErrors().get(0).getDefaultMessage());
            return TokenDTO.builder().token("").build();
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return TokenDTO
                .builder()
                .token(jwtTokenUtil.getToken(userDetailsService.loadUserByUsername(authenticationDTO.getEmail())))
                .build();

    }



}
