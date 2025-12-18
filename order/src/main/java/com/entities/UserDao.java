package com.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<Users, Long> {

	@Query("select c from Users c where c.employeeHrmsId=:employeeHrmsId")
	Users getUserForEmployeeHrmsId(@Param("employeeHrmsId") String employeeHrmsId);

}
