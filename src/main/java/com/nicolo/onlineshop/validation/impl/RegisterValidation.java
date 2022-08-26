package com.nicolo.onlineshop.validation.impl;
import com.nicolo.onlineshop.entity.Login;
import com.nicolo.onlineshop.repository.LogInRepository;
import com.nicolo.onlineshop.validation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterValidation implements ValidationService<String> {

    @Autowired
    LogInRepository logInRepository;

    @Override
    public boolean checkIfExist(String email) {
        Optional<List<Login>>  logins = logInRepository.findByEmail(email);
        if(logins.isPresent() && !logins.get().isEmpty()){
            return true;
        }
        return false;
    }


}
