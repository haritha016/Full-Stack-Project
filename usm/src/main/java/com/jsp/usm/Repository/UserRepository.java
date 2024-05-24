package com.jsp.usm.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.usm.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	@Query(value="from UserEntity where email=:email")
	public UserEntity findByEmail(String email);
	
	
	public UserEntity findByContactNumberAndEmail(long contactNumber,String email);
	
	public List<UserEntity> findByName(String name);
	
	public List<UserEntity> findByNameAndEmailOrContactNumberOrderByEmailAsc(String name,String email,long contactNumber);

	@Transactional
	@Modifying
	@Query(value = "update UserEntity set name=:n,city=:c,pinCode=:p where altkey=:id")
    public void updateUserDetails(@Param("n") String name,@Param("c") String city,@Param("p") long pinCode,@Param("id") long id);

}
