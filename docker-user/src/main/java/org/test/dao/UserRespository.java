package org.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.entity.User;
/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface UserRespository extends JpaRepository<User, Integer> {

}
