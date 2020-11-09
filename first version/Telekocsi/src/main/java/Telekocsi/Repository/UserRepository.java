package Telekocsi.Repository;

import Telekocsi.Model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM USERS u", nativeQuery=true)
    public List<User> getAllUsers();

    @Query("SELECT u FROM User u WHERE u.username=?1 and u.password=?2")
    public List<User> findUserLogin(String username, String password);

    @Query("SELECT u FROM User u WHERE u.id=?1")
    public List<User> getUserById(int id);

    //void newUser(@Param("username")String username, @Param("password")String password, @Param("email")String email, @Param("realname")String realname);
}
