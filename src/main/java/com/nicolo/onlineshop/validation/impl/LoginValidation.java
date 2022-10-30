package com.nicolo.onlineshop.validation.impl;
import com.nicolo.onlineshop.dto.LoginRequest;
import com.nicolo.onlineshop.entity.Login;
import com.nicolo.onlineshop.repository.LogInRepository;
import com.nicolo.onlineshop.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LoginValidation implements ValidationService<LoginRequest> {

    @Autowired
    private LogInRepository logInRepository;

    @Override
    public boolean checkIfExist(LoginRequest arg) {
        Optional<List<Login>> logins = logInRepository.findByEmailAndPassword(arg.getEmail(),arg.getPassword());
        return logins.isPresent() && !logins.get().isEmpty();
    }

    @Override
    public boolean checkValidParameters(LoginRequest arg) throws Exception {
        return false;
    }


}
