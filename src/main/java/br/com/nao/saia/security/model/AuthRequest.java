package br.com.nao.saia.security.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class AuthRequest {

    @NotNull(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String username;

    @NotNull(message = "Senha do usuário é obrigatória")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
