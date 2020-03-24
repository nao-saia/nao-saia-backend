package br.com.nao.saia.converter;

import br.com.nao.saia.dto.UserDTO;
import br.com.nao.saia.model.User;

public final class UserConverter {

    private UserConverter() {
    }

    public static User fromDTOToDomain(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAcceptTerms(userDTO.isAcceptTerms());
        user.setCreatedAt(userDTO.getCreatedAt());
        user.setUpdateAt(userDTO.getUpdateAt());
        return user;
    }

    public static UserDTO fromDomainToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAcceptTerms(user.isAcceptTerms());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdateAt(user.getUpdateAt());
        return userDTO;
    }

}
