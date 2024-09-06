package com.somos_sansa.sansa.config.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.somos_sansa.sansa.models.entities.Role;
import com.somos_sansa.sansa.repositories.RoleRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private RoleRepository roleRepository;

    public DataLoader (RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0) {
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));
        }
    }
}
