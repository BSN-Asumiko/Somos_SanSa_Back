package com.somos_sansa.sansa.services;

import java.util.Optional;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.models.entities.Role;
import com.somos_sansa.sansa.models.entities.User;
import com.somos_sansa.sansa.repositories.RoleRepository;
import com.somos_sansa.sansa.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> addNewUser(User user) throws SanSaException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new SanSaException ("No se registró, porque el email ya está siendo utilizado.",
                    HttpStatus.CONFLICT);
        }

        User savedUser = userRepository.save(user);

        Role defaultRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        savedUser.getRoles().add(defaultRole);
        userRepository.save(savedUser);
        return new ResponseEntity<>("El usuario se ha registrado con exito!", HttpStatus.CREATED);
    }

    public User getUserById(int userId) throws SanSaException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new SanSaException("Usuario no encontrado.", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUserProfile(User user) throws SanSaException {

        try {
            User existingUser = getUserById(user.getId());

            existingUser.setNickname(user.getNickname());
            existingUser.setAvatarUrl(user.getAvatarUrl());
            existingUser.setDistrict(user.getDistrict());
            
            userRepository.save(existingUser);
        
        } catch (IllegalArgumentException | OptimisticLockingFailureException | JpaObjectRetrievalFailureException exc) {
            throw new SanSaException("Failed to update user profile: " + exc.getMessage());
        }
    }

    //DESCOMMENT WHEN SECURITY IS CONFIGURED
    /* public Optional<User> getUserFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername(); 
            Optional<User> requestedUser = getUserByEmail(email);
            if (!requestedUser.isPresent()) {
                throw new RuntimeException("Usuario no autenticado");
            } 
            return requestedUser;
        } else {
            throw new RuntimeException("Usuario no autenticado");
        }
    } */
}
