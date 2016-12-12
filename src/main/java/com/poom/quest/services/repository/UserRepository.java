package com.poom.quest.services.repository;

import org.springframework.stereotype.Repository;

import com.poom.quest.services.domain.user.User;

@Repository
public class UserRepository extends GenericRepository<User, Long> {

}
