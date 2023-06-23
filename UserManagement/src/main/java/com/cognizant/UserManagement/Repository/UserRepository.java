package com.cognizant.UserManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.UserManagement.Entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	@Modifying(clearAutomatically = true)
	@Query("update USER u set u.password = :password where u.username= :username")
	void updatePassword(@Param("password") String password,@Param("username") String username);

//	@Modifying(clearAutomatically = true)
//	@Query(value = "update user set password = ?1 where username= ?2", nativeQuery = true)
//	public void updatePassword(String password, String username);

}
