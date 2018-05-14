package br.com.douglasffilho.UserServices.VO;

import br.com.douglasffilho.UserServices.entities.User;
import br.com.douglasffilho.UserServices.utils.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    @NotNull(message = "Informe o nome do usuário.")
    private String name;

    @NotNull(message = "Infome o e-mail do usuário.")
    private String email;

    @NotNull(message = "Informe uma senha válida.")
    private String password;

    @NotNull(message = "O telefone do usuário é necessário.")
    private String phone;

    @NotNull(message = "Informe o nível de acesso deste usuário.")
    private ProfileEnum role;

    public User toUsuario() {
        return User.buildNewUserValidBy1Year(this.name, this.email, this.password, this.phone, this.role);
    }
}
