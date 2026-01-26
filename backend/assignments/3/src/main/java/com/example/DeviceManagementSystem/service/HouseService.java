package com.example.DeviceManagementSystem.service;

import com.example.DeviceManagementSystem.dto.HouseCreationRequestDTO;
import com.example.DeviceManagementSystem.dto.HouseCreationResponseDTO;
import com.example.DeviceManagementSystem.dto.RoomCreationRequestDTO;
import com.example.DeviceManagementSystem.dto.RoomCreationResponseDTO;
import com.example.DeviceManagementSystem.entity.House;
import com.example.DeviceManagementSystem.entity.Room;
import com.example.DeviceManagementSystem.entity.User;
import com.example.DeviceManagementSystem.entity.UsersInHouse;
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
}
