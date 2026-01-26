package com.example.DeviceManagementSystem.service;

import com.example.DeviceManagementSystem.dto.*;
import com.example.DeviceManagementSystem.entity.House;
import com.example.DeviceManagementSystem.entity.Room;
import com.example.DeviceManagementSystem.entity.User;
import com.example.DeviceManagementSystem.entity.UsersInHouse;
import com.example.DeviceManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.DeviceManagementSystem.repository.HouseRepository;
import com.example.DeviceManagementSystem.repository.RoomRepository;
import com.example.DeviceManagementSystem.repository.UserRepository;
import com.example.DeviceManagementSystem.repository.UsersInHouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UsersInHouseRepository usersInHouseRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public HouseService(HouseRepository houseRepository, UsersInHouseRepository usersInHouseRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.houseRepository = houseRepository;
        this.usersInHouseRepository = usersInHouseRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    public boolean userExists(String userEmail) {
        return userRepository.existsByEmail(userEmail);
    }

    public boolean userExistsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Transactional
    public HouseCreationResponseDTO createHouse(HouseCreationRequestDTO houseCreationRequestDTO) {
        String userEmail = houseCreationRequestDTO.getEmail();
        String houseName = houseCreationRequestDTO.getHouse_name();
        String address = houseCreationRequestDTO.getAddress();

        if(!userExists(userEmail)) {
            throw new RuntimeException("User doesn't exist");
        }

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));


        if(houseRepository.existsByHouseName(houseName,user.getId()) == 1L) {
            throw new RuntimeException("User already has the house with same name, please create house with different name");
        }

        House house = new House(UUID.randomUUID().toString(),user,address,houseName);
        houseRepository.save(house);

        UsersInHouse usersInHouse = new UsersInHouse(house,user,true);
        usersInHouseRepository.save(usersInHouse);


        return new HouseCreationResponseDTO(house.getId(),houseName,address,user.getUserName());
    }

    public boolean isAdmin(Long userId, String houseId) {
        return Boolean.TRUE.equals(usersInHouseRepository.checkIsAdmin(userId,houseId));
    }

    @Transactional
    public RoomCreationResponseDTO createRoom(RoomCreationRequestDTO roomCreationRequestDTO) {
        String userEmail = roomCreationRequestDTO.getEmail();
        String roomName = roomCreationRequestDTO.getRoomName();
        String houseId = roomCreationRequestDTO.getHouseId();

        if(!userExists(userEmail)) {
            throw new RuntimeException("User doesn't exist");
        }

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        if(!isAdmin(user.getId(), houseId)) {
            throw new RuntimeException("Un-authorized access, you can't create rooms in house"+houseId);
        }

        if(roomRepository.existsByRoomName(roomName,houseId) == 1L) {
            throw new RuntimeException("Room with this name already exists in the house");
        }

        House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House by id: "+houseId+" doesn't exist"));
        Room room = new Room(roomName,house);

        roomRepository.save(room);

        return new RoomCreationResponseDTO(roomName,room.getId(),house.getHouse_name(),user.getUserName());
    }

    public UserRegisterToHouseResponseDTO registerUserToHouse(UserRegisterToHouseRequestDTO userRegisterToHouseRequestDTO) {
        String userEmail = userRegisterToHouseRequestDTO.getEmail();
        String houseId = userRegisterToHouseRequestDTO.getHouseId();
        Long userToBeAddedId = userRegisterToHouseRequestDTO.getUserToRegisterId();

        if(!userExists(userEmail)) {
            throw new RuntimeException("Admin User doesn't exist");
        }

        System.out.println("admin exists");

        if(!userExistsById(userToBeAddedId)) {
            throw new RuntimeException("User to add doesn't exist");
        }

        System.out.println("user to add exists");

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));

        System.out.println("got admin");

        if(!isAdmin(user.getId(), houseId)) {
            throw new RuntimeException("Un-authorized access, you can't create rooms in house"+houseId);
        }

        System.out.println("admin error");


        if(usersInHouseRepository.checkIfUserExistsById(userToBeAddedId,houseId) == 1L) {
            throw new ResourceAlreadyExistsException("User to be added already exists in the house");
        }

        System.out.println("user to add exists in house");


        User userToBeAdded = userRepository
                .findById(userToBeAddedId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist"));
        House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House by id: "+houseId+" doesn't exist"));
        UsersInHouse newUser = new UsersInHouse(house,userToBeAdded,false);

        System.out.println("user added to house");

        usersInHouseRepository.save(newUser);

        return new UserRegisterToHouseResponseDTO(userToBeAdded.getUserName(),houseId);
    }
}
