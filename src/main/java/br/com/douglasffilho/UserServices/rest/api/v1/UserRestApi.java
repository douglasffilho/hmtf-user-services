package br.com.douglasffilho.UserServices.rest.api.v1;

import br.com.douglasffilho.UserServices.dto.UserDTO;
import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.rest.api.endpoints.ApiV1Endpoints;
import br.com.douglasffilho.UserServices.services.UserService;
import br.com.douglasffilho.UserServices.utils.impl.UserFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ApiV1Endpoints.API_V1_USERS_ROOT_ENDPOINT)
@Api(value = ApiV1Endpoints.API_V1_USERS_ROOT_ENDPOINT, description = "API para consulta e manutenção de usuarios do sistema.")
public class UserRestApi {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Lista com os usuários do sistema")
	@RequestMapping(value = "", method = RequestMethod.GET)
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

	@ApiOperation(value = "Obter dados de usuário por nome")
	@RequestMapping(value = ApiV1Endpoints.API_V1_USERS_FIND_BY_NAME_ENDPOINT, method = RequestMethod.GET)
	public User obterUsuarioPorNome(@PathVariable("name") String name, HttpServletResponse response)
			throws IOException {
		try {
			User user = userService.findByUsername(name);

			return user;
		} catch (ServiceException ex) {
			log.error("M=obterUsuarioPorNome, E=Erro ao tentar encontrar user. Verifique o stacktrace seguinte:");
			ex.printStackTrace();

			response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
			return null;
		}
	}

	@ApiOperation(value = "Adicionar usuário")
	@RequestMapping(value = ApiV1Endpoints.API_V1_USERS_SAVE_ENDPOINT, method = RequestMethod.POST)
	public User salvarUsuario(@RequestBody @Valid UserDTO usuario, HttpServletResponse response)
			throws IOException {
		try {
			User newUser = UserFactory
					.builder()
					.username(usuario.getName())
					.email(usuario.getEmail())
					.phone(usuario.getPhone())
					.password(usuario.getPassword())
					.role(usuario.getRole())
					.build()
					.createValid();
			return userService.save(newUser);
		} catch (ServiceException ex) {
			log.error("M=salvarUsuario, E=Erro ao tentar salvar user. Verifique o stacktrace seguinte:");
			ex.printStackTrace();

			response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
			return null;
		}
	}
}
