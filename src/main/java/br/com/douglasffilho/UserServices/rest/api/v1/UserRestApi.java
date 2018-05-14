package br.com.douglasffilho.UserServices.rest.api.v1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import br.com.douglasffilho.UserServices.Messaging.MessageSender;
import br.com.douglasffilho.UserServices.VO.UserVO;
import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.services.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.douglasffilho.UserServices.rest.api.endpoints.ApiV1Endpoints;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(value = ApiV1Endpoints.API_V1_USERS_ROOT_ENDPOINT, //
        description = "API para consulta e manutenção de usuarios do sistema.")
@RestController
@RequestMapping(ApiV1Endpoints.API_V1_USERS_ROOT_ENDPOINT)
@Slf4j
public class UserRestApi {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSender messageSender;

    private void sendMessageToQueue(User user) {
        try {
            messageSender.send("UserServicesExchange", "UserServices", user);
        } catch (JsonProcessingException jpe) {
            log.error(
                    "M=sendMessageToQueue, E=Erro ao tentar converter objeto em json. Verifique o stacktrace seguinte:");
            jpe.printStackTrace();
        }
    }

    @ApiOperation(value = "", //
            notes = "Obter todos os usuários do sistema")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> usuarios(HttpServletResponse response) throws IOException {
        try {
            List<User> users = userService.list();

            return users;
        } catch (ServiceException ex) {
            log.error("M=usuarios, E=Erro ao tentar obter usuarios. Verifique o stacktrace seguinte:");
            ex.printStackTrace();

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return new ArrayList<>();
        }
    }

    @ApiOperation(value = ApiV1Endpoints.API_V1_USERS_FIND_BY_NAME_ENDPOINT, //
            notes = "Obter dados de usuário por nome")
    @RequestMapping(value = ApiV1Endpoints.API_V1_USERS_FIND_BY_NAME_ENDPOINT, method = RequestMethod.GET)
    public User obterUsuarioPorNome(@PathVariable("name") String name, HttpServletResponse response)
            throws IOException {
        try {
            User user = userService.findByName(name);

            return user;
        } catch (ServiceException ex) {
            log.error("M=obterUsuarioPorNome, E=Erro ao tentar encontrar user. Verifique o stacktrace seguinte:");
            ex.printStackTrace();

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return null;
        }
    }

    @ApiOperation(value = ApiV1Endpoints.API_V1_USERS_SAVE_ENDPOINT, //
            notes = "Criar novo usuário")
    @RequestMapping(value = ApiV1Endpoints.API_V1_USERS_SAVE_ENDPOINT, method = RequestMethod.POST)
    public User salvarUsuario(@RequestBody @Valid UserVO usuario, HttpServletResponse response)
            throws IOException {
        try {
            User novoUser = usuario.toUsuario();
            novoUser = userService.save(novoUser);

            sendMessageToQueue(novoUser);

            return novoUser;
        } catch (ServiceException ex) {
            log.error("M=salvarUsuario, E=Erro ao tentar salvar user. Verifique o stacktrace seguinte:");
            ex.printStackTrace();

            response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return null;
        }
    }
}
