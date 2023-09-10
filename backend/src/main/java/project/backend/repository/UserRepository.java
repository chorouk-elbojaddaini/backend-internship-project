package project.backend.repository;

import project.backend.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserDAO,Long> {
    List<UserDAO> findAll();


}
