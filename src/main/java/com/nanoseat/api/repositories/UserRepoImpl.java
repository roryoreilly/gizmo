package com.nanoseat.api.repositories;

import com.nanoseat.api.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class UserRepoImpl implements UserRepoCustom{

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> getUsersToSendBalance() {
        return entityManager.createQuery(
                "select u " +
                        " from User u where isMoneySent = false", User.class)
                .getResultList();

    }
}
