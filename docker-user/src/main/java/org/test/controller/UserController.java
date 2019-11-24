package org.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 
 * @author Administrator
 *
 */
import org.test.dao.UserRespository;
import org.test.entity.User;

import com.netflix.discovery.EurekaClient;
@RestController
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRespository userRespository;
	
	@GetMapping("/simple/{id}")
	public User findById(@PathVariable("id") Integer id) {
		logger.info("获取用户信息：" +id );
		User user =  userRespository.getOne(id);
		System.out.println(user.getAccount() + " -- " + user.getIdcard());
		return user;
	}
	
	@GetMapping("list")
	public List<User> list() {
		logger.info("获取所有用户信息：");
		List<User> users =  userRespository.findAll();
		logger.info("获取所有用户信息：{}", users.size());
		return users;
	}
}
