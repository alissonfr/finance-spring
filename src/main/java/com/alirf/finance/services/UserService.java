package com.alirf.finance.services;

import com.alirf.finance.models.User;
import com.alirf.finance.repositories.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> find(String name, Pageable page) {
        return userRepository.findAll((root, query, builder) -> builder.and(filterUsers(root, builder, name)), page);
    }

    private Predicate[] filterUsers(Root root, CriteriaBuilder builder, String name) {
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) predicates.add(builder.like(builder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));

        return predicates.toArray(Predicate[]::new);
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado:  " + id));
    }

    public User update(UUID id, User user) {
        User userExists = get(id);

        return this.userRepository.save(user);
    }


}
