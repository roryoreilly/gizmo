package com.nanoseat.api.repositories;

import com.nanoseat.api.entity.User;

import java.util.List;

public interface UserRepoCustom{
    List<User> getUsersToSendBalance();
}
