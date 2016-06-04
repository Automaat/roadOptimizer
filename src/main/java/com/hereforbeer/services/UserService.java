package com.hereforbeer.services;

import com.hereforbeer.domain.User;
import com.hereforbeer.repositories.UserRepository;
import com.hereforbeer.web.BadRequestException;
import com.hereforbeer.web.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hereforbeer.web.ErrorInfo.USER_DUPLICATED;
import static com.hereforbeer.web.ErrorInfo.USER_NOT_FOUND;
import static com.hereforbeer.web.ErrorInfo.WRONG_PASSWORD;
import static com.hereforbeer.web.dto.DTOMapper.parseUserFromDTO;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(UserDTO userDTO) {
        User user = parseUserFromDTO(userDTO);
        userRepository.findOneByNick(user.getNick()).ifPresent((u) -> {
            throw new BadRequestException(USER_DUPLICATED);
        });

        userRepository.save(user);
    }

    public void login(String nick, String password) {
        Optional<User> userFromDB = userRepository.findOneByNick(nick);

        userFromDB.orElseThrow(() -> new BadRequestException(USER_NOT_FOUND));

        userFromDB
                .ifPresent(u -> {
                    if (!u.getPassword().equals(password)) {
                        throw new BadRequestException(WRONG_PASSWORD);
                    }
                });
    }
}
