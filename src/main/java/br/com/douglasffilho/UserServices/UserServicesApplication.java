package br.com.douglasffilho.UserServices;

import br.com.douglasffilho.UserServices.rest.api.SwaggerConfig;
import br.com.douglasffilho.UserServices.security.WebSecurityConfig;
import br.com.douglasffilho.UserServices.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@Slf4j
@SpringBootApplication
@Import({WebSecurityConfig.class, SwaggerConfig.class})
public class UserServicesApplication {

    @Autowired
    private UserService userService;

    @PostConstruct
    private void init() {
        try {
            userService.createDefaultUser();
        } catch(ServiceException sex) {
            log.error("M=UserServicesApplication.init, E=Erro ao tentar criar usuario padrao: {}", sex.getMessage(), sex);
        }
    }

    public static void main(final String[] args) {
        SpringApplication.run(UserServicesApplication.class, args);
    }
}
