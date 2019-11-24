package org.test.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/simple")
public class SimpleController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Value("${simple.user.name:default}")
	private String envName;
	
	@GetMapping("/now")
	public String simple(String name) {
		System.out.println("name is : " + name);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return "envName:" + envName + ",hello:" + name + ", now is : " + dateTimeFormatter.format(LocalDateTime.now());
	}
	
	@GetMapping("/user/{id}")
	public String user(@PathVariable Integer id) {
		String user = restTemplate.getForEntity("http://DOCKER-USER/users/simple/{id}", String.class, id).getBody();
		return user;
	}
	
	@GetMapping("/userlist")
	public String userList(String name) {
		
		StringBuilder sb = new StringBuilder();
		ServiceInstance serviceinstance = loadBalancerClient.choose("DOCKER-USER");
		//sb.append("-----------------user----------------");
		sb.append(serviceinstance.getServiceId()+":" + serviceinstance.getHost()+":" + serviceinstance.getPort());
		//sb.append("-----------------user2----------------");
		System.out.println(sb.toString());
	
		
		
		String users = restTemplate.getForEntity("http://DOCKER-USER/users/list", String.class).getBody();
		return users;
	}
	
}
