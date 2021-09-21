package com.travel.lodge.userservice.repository;

import com.travel.lodge.userservice.domain.HotelUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface HotelUserRepository extends CrudRepository<HotelUser, Long> {
    Optional<HotelUser> findByEmail(String email);

    List<HotelUser> findAll();

}
