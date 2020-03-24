package br.com.nao.saia.dto;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull(message = "Email do usuário é obrigatório")
    private String email;

    @NotNull(message = "Senha do usuário é obrigatória")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
