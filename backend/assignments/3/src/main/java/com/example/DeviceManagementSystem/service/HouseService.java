package com.example.DeviceManagementSystem.service;

import com.example.DeviceManagementSystem.dto.*;
import com.example.DeviceManagementSystem.entity.*;
import com.example.DeviceManagementSystem.exception.NotFoundException;
import com.example.DeviceManagementSystem.exception.ResourceAlreadyExistsException;
import com.example.DeviceManagementSystem.exception.UnAuthorizedAccessException;
import com.example.DeviceManagementSystem.exception.UserNotFoundException;
import com.example.DeviceManagementSystem.repository.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final UsersInHouseRepository usersInHouseRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final DeviceAssignmentRepository deviceAssignmentRepository;
    private final CentralDeviceInventoryRepository centralDeviceInventoryRepository;


    public HouseService(HouseRepository houseRepository, UsersInHouseRepository usersInHouseRepository, UserRepository userRepository, RoomRepository roomRepository,  DeviceAssignmentRepository deviceAssignmentRepository, CentralDeviceInventoryRepository centralDeviceInventoryRepository) {
        this.houseRepository = houseRepository;
        this.usersInHouseRepository = usersInHouseRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.deviceAssignmentRepository = deviceAssignmentRepository;
        this.centralDeviceInventoryRepository = centralDeviceInventoryRepository;
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
            throw new UserNotFoundException("User with email: "+userEmail+" doesn't exist");
        }

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User with email: "+userEmail+" doesn't exist"));


        if(houseRepository.existsByHouseName(houseName,user.getId()) == 1L) {
            throw new ResourceAlreadyExistsException("User already has the house with same name, please create house with different name");
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
            throw new UserNotFoundException("User with email: "+userEmail+" doesn't exist");
        }

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("User with email: "+userEmail+" doesn't exist"));

        if(!isAdmin(user.getId(), houseId)) {
            throw new UnAuthorizedAccessException("Un-authorized access, you can't create rooms in house with id: "+houseId);
        }

        if(roomRepository.existsByRoomName(roomName,houseId) == 1L) {
            throw new ResourceAlreadyExistsException("Room with this name already exists in the house");
        }

        House house = houseRepository.findById(houseId).orElseThrow(() -> new NotFoundException("House by id: "+houseId+" doesn't exist"));
        Room room = new Room(roomName,house);

        roomRepository.save(room);

        return new RoomCreationResponseDTO(roomName,room.getId(),house.getHouse_name(),user.getUserName());
    }

    @Transactional
    public UserRegisterToHouseResponseDTO registerUserToHouse(UserRegisterToHouseRequestDTO userRegisterToHouseRequestDTO) {
        String userEmail = userRegisterToHouseRequestDTO.getEmail();
        String houseId = userRegisterToHouseRequestDTO.getHouseId();
        Long userToBeAddedId = userRegisterToHouseRequestDTO.getUserToRegisterId();

        if(!userExists(userEmail)) {
            throw new UserNotFoundException("Admin with email: "+userEmail+" doesn't exist");
        }

        System.out.println("admin exists");

        if(!userExistsById(userToBeAddedId)) {
            throw new UserNotFoundException("User to add userID:"+userToBeAddedId+" doesn't exist");
        }

        System.out.println("user to add exists");

        User user = userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFoundException("Admin with email: "+userEmail+" doesn't exist"));

        System.out.println("got admin");

        if(!isAdmin(user.getId(), houseId)) {
            throw new UnAuthorizedAccessException("Un-authorized access, you can't add users to house with id: "+houseId);
        }

        System.out.println("admin error");


        if(usersInHouseRepository.checkIfUserExistsById(userToBeAddedId,houseId) == 1L) {
            throw new ResourceAlreadyExistsException("User to be added already exists in the house");
        }

        System.out.println("user to add exists in house");


        User userToBeAdded = userRepository
                .findById(userToBeAddedId)
                .orElseThrow(() -> new UserNotFoundException("User to add userID:"+userToBeAddedId+" doesn't exist"));
        House house = houseRepository.findById(houseId).orElseThrow(() -> new NotFoundException("House by id: "+houseId+" doesn't exist"));
        UsersInHouse newUser = new UsersInHouse(house,userToBeAdded,false);

        System.out.println("user added to house");

        usersInHouseRepository.save(newUser);

        return new UserRegisterToHouseResponseDTO(userToBeAdded.getUserName(),houseId);
    }

    @Transactional
    public DeviceAssignmentResponseDTO assignDeviceToRooms(DeviceAssignmentRequestDTO deviceAssignmentRequestDTO) {
        String kickstonId = deviceAssignmentRequestDTO.getKickston_id();
        String houseId = deviceAssignmentRequestDTO.getHouseId();
        Integer roomId = deviceAssignmentRequestDTO.getRoomId();
        Long userId = deviceAssignmentRequestDTO.getUserId();

        if(!userExistsById(userId)) {
            throw new UserNotFoundException("User with id: "+userId+" doesn't exist");
        }
        System.out.println("User exists");

        if(!houseRepository.existsById(houseId)) {
            throw new NotFoundException("House with id: "+houseId+" doesn't exist");
        }

        System.out.println("House exists");


        if(usersInHouseRepository.checkIfUserExistsById(userId,houseId) != 1L) {
            throw new UserNotFoundException("User with id: "+userId+" not found in the house with id: "+houseId);
        }

        System.out.println("User is in house");

        if(deviceAssignmentRepository.checkIfDeviceExistsInRoom(kickstonId,houseId) != 1L) {
            throw new NotFoundException("Device with id: "+kickstonId+" is not in house with id: "+houseId);
        }

        System.out.println("device is in house");

        if(deviceAssignmentRepository.changeDeviceRoom(kickstonId,houseId,roomId) != 1L) {
            throw new RuntimeException("Something went wrong");
        }

        System.out.println("Moved device to another room");

        return new DeviceAssignmentResponseDTO(kickstonId,roomId,houseId);
    }


    @Transactional
    public DeviceAssignmentToHouseResponseDTO assignDeviceToHouse(DeviceAssignmentToHouseDTO deviceAssignmentToHouseDTO) {
        String kickstonId = deviceAssignmentToHouseDTO.getKickston_id();
        String houseId = deviceAssignmentToHouseDTO.getHouseId();
        Long userId = deviceAssignmentToHouseDTO.getUserId();
        String devicePassword = deviceAssignmentToHouseDTO.getDevice_password();
        String deviceUserName = deviceAssignmentToHouseDTO.getDevice_username();

        if(!userExistsById(userId)) {
            throw new UserNotFoundException("Admin with id: "+userId+" doesn't exist");
        }

        System.out.println("User exists");

        if(!houseRepository.existsById(houseId)) {
            throw new NotFoundException("House with id: "+houseId+" doesn't exist");
        }

        System.out.println("house exists");


        if(!isAdmin(userId, houseId)) {
            throw new UnAuthorizedAccessException("Un-authorized access, you can't add devices to house with id: "+houseId);
        }

        System.out.println("User is admin");

        if(deviceAssignmentRepository.checkIfDeviceExistsInRoom(kickstonId,houseId) == 1L) {
            throw new ResourceAlreadyExistsException("Device is already in house");
        }

        System.out.println("device not in house");

        if(deviceAssignmentRepository.checkIfDeviceExistsInDifferentHouse(kickstonId,houseId) == 1L) {
            throw new ResourceAlreadyExistsException("Device is in another house, so can't add it to current house");
        }

        System.out.println("device not in another house");

        if(centralDeviceInventoryRepository.checkIfGivenDeivceExists(kickstonId,deviceUserName,devicePassword) != 1L) {
            throw new BadCredentialsException("Either device doesn't exist or given credentials are wrong");
        }

        System.out.println("credentials are good and device exists in central repo");

        CentralDeviceInventory device = centralDeviceInventoryRepository.findById(kickstonId).orElseThrow(() -> new NotFoundException("Device with id: "+kickstonId+" doesn't exist"));
        House house = houseRepository.findById(houseId).orElseThrow(() -> new NotFoundException("House by id: "+houseId+" doesn't exist"));

        DeviceAssignment deviceAssignment = new DeviceAssignment(device,house,null);
        deviceAssignmentRepository.save(deviceAssignment);

        System.out.println("device added");

        return new DeviceAssignmentToHouseResponseDTO(kickstonId,houseId);
    }

    public DevicesInHouseResponseDTO getAllDevicesFromHouse(DevicesInHouseRequestDTO devicesInHouseRequestDTO) {
        String houseId = devicesInHouseRequestDTO.getHouseId();
        Long userId = devicesInHouseRequestDTO.getUserId();

        if(!userExistsById(userId)) {
            throw new UserNotFoundException("User with id: "+userId+" doesn't exist");
        }

        if(!houseRepository.existsById(houseId)) {
            throw new NotFoundException("House with id: "+houseId+" doesn't exist");
        }

        if(usersInHouseRepository.checkIfUserExistsByIdIncludingAdmin(userId,houseId) != 1L) {
            throw new UnAuthorizedAccessException("User with id: "+userId+" not in the house with id: "+houseId+" , so this action is prohibited");
        }

        List<String> devicesList = deviceAssignmentRepository.getAllDevicesInHouse(houseId);

        return new DevicesInHouseResponseDTO(devicesList);
    }

    public List<String> getAllHousesOfUser(Long userId) {
        if(!userExistsById(userId)) {
            throw new UserNotFoundException("User with id: "+userId+" doesn't exist");
        }

        return usersInHouseRepository.getAllHousesWhereUserBelongs(userId);
    }
}
