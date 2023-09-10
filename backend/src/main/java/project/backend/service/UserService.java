package project.backend.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.backend.dao.UserDAO;
import project.backend.dto.UserDTO;
import project.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<UserDAO> findUserById(Long id){
        Optional<UserDAO> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new EntityNotFoundException("User with ID"+id+" not found");
        }
        return user;
    }
    public UserDAO addUser(UserDTO user){
        UserDAO newUser = UserDAO.builder()
                .name(user.getName())
                .job(user.getJob())
                .image(user.getImage())
                .build();

        return userRepository.save(newUser);
    }

    public List<UserDAO> getAllUsers(){
        return userRepository.findAll();
    }

    public boolean deleteUser(Long id){
        Optional<UserDAO> user = userRepository.findById(id);

        if(user.isPresent()){
            userRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public UserDAO updateUser(UserDTO user,Long id){
        UserDAO userToUpdate = userRepository.findById(id).get();
        if(user.getName() != null){
            userToUpdate.setName(user.getName());
        }
        if(user.getJob() != null){
            userToUpdate.setJob(user.getJob());
        }
        if(user.getImage() != null){
            userToUpdate.setImage(user.getImage());
        }
        return userRepository.save(userToUpdate);

    }
}
