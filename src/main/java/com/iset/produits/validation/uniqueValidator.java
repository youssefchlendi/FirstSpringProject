package com.iset.produits.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.iset.produits.annotaion.uniqueConstraint;
import com.iset.produits.dao.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class uniqueValidator implements ConstraintValidator<uniqueConstraint, String> {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void initialize(uniqueConstraint uniqueConstraint) {
    }

    @Override
    public boolean isValid(String username,
      ConstraintValidatorContext cxt) {
        return !userRepository.findUserWithName(username).isPresent();
    }
}
