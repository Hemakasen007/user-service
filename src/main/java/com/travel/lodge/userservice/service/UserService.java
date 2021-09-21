package com.travel.lodge.userservice.service;

import com.travel.lodge.userservice.domain.HotelUser;
import com.travel.lodge.userservice.dto.*;
import com.travel.lodge.userservice.exception.UserCreationException;
import com.travel.lodge.userservice.exception.UserDoesNotExistException;
import com.travel.lodge.userservice.exception.UserExistsException;
import com.travel.lodge.userservice.feign.AuthServiceClient;
import com.travel.lodge.userservice.repository.HotelUserRepository;
import com.travel.lodge.userservice.util.Consts;
import com.travel.lodge.userservice.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.travel.lodge.userservice.util.Consts.AppMessages.UPDATED_USER;
import static com.travel.lodge.userservice.util.Consts.Log.ENTERED;
import static com.travel.lodge.userservice.util.Consts.ResponseMessages.SUCCESS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final AuthServiceClient authServiceClient;
    final ModelMapper modelMapper;
    final HotelUserRepository hotelUserRepository;
    final EncryptionUtil encryptionUtil;

    @Transactional
    public CommonResponse addHotelUser(AddUserRequest request) {
        log.info(Consts.Log.BRACKETS_3, ENTERED, "addHotelUser", request.getEmail());
        //check if user already exists
        Optional<HotelUser> hotelUserOptional = hotelUserRepository.findByEmail(request.getEmail());
        if (hotelUserOptional.isPresent()) {
            log.error(Consts.Log.USER_EXISTS);
            throw new UserExistsException();
        }

        var password = encryptionUtil.encrypt(request.getPassword());
        var keyUser = new KeycloakUser(request.getFirstName(), request.getLastName(), request.getEmail(), password);
        ResponseEntity<Object> response;
        try {
            response = authServiceClient.createUser(keyUser);
            if (response.getStatusCodeValue() == 201) {
                var hotelUser = modelMapper.map(request, HotelUser.class);
                hotelUser.setActivityStatus(Consts.ConstString.ACTIVE_USER_REG);
                hotelUser.setContactNo(request.getContactNo());
                hotelUser.setLocation(request.getLocation());
                return new CommonResponse(SUCCESS.name(),
                        Consts.AppMessages.USER_CREATED, hotelUser);
            } else {
                throw new UserExistsException();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new UserCreationException(e);
        }

    }


    public CommonResponse getAllUsers() {
        log.info(Consts.Log.BRACKETS_2, ENTERED, "getAllUsers");
        List<HotelUser> users = hotelUserRepository.findAll();
        return new CommonResponse(SUCCESS.name(), null, users);
    }

    public CommonResponse getUserBasedOnId(long id) {
        log.info(Consts.Log.BRACKETS_2, ENTERED, "getUserBasedOnId");
        var hotelUser = hotelUserRepository.findById(id).orElseThrow(UserDoesNotExistException::new);
        return new CommonResponse(SUCCESS.name(), null, hotelUser);
    }

    @Transactional
    public CommonResponse updateUser(long id, UpdateUserRequest request) {
        log.info(Consts.Log.BRACKETS_2, ENTERED, "updateUser");
        var hotelUser = hotelUserRepository.findById(id).orElseThrow(UserDoesNotExistException::new);
        var activeStatus = getStatusUpdate(request.getActivityStatus());
        if (request.getContactNo() != null)
            hotelUser.setContactNo(request.getContactNo());
        if (request.getLocation() != null)
            hotelUser.setLocation(request.getLocation());
        if (request.getFirstName() != null)
            hotelUser.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            hotelUser.setLastName(request.getLastName());
        if(activeStatus!=-1)
            hotelUser.setActivityStatus(activeStatus);
        hotelUserRepository.save(hotelUser);

        return new CommonResponse(SUCCESS.name(), UPDATED_USER, hotelUser);
    }

    private int getStatusUpdate(String status){
        if(status==null)
            return -1;
        if(status.equalsIgnoreCase(Consts.UserStatus.ACTIVATE.name())) {
            return 1;
        }else{
            return 0;
        }

    }

    public HotelUserDetails getDetailsByUsername(String username) {
        var hotelUser = hotelUserRepository.findByEmail(username).orElseThrow(UserDoesNotExistException::new);
        return modelMapper.map(hotelUser, HotelUserDetails.class);
    }
}
