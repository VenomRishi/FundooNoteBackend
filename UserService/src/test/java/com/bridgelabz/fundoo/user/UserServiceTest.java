package com.bridgelabz.fundoo.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import com.bridgelabz.fundoo.user.configuration.UserConfiguration;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.entity.User;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.service.ImplUserService;


@SpringBootTest
public class UserServiceTest {

	@Mock
	private UserRepository repository;

	@Mock
	private UserConfiguration config;

	@Mock
	private RabbitTemplate rabbitTemplate;

	@InjectMocks
	private ImplUserService userService;

	@Mock
	private ModelMapper modelMapper;

	@Test
	public void testRegister() {
		User user=new User();
		RegisterDTO regDTO = new RegisterDTO();
		regDTO.setFname("Abhishek");
		regDTO.setLname("Rawat");
		regDTO.setEmail("rawatabhishek2012@gmail.com");
		regDTO.setPassword("123456");
		user.setEmail(regDTO.getEmail());
		
//		when(config.passwordEncoder().encode(regDTO.getPassword())).thenReturn("abc");
//		when(repository.findByEmail(regDTO.getEmail())).thenReturn(Optional.of(user));
//		when(repository.findByEmail(regDTO.getEmail()).isPresent()).thenThrow(new RegisterException(regDTO.getEmail() + " " + Constant.REGISTER_EMAIL_FOUND));
		
//		when(config.modelMapper().map(regDTO, User.class)).thenReturn(user);
		when(modelMapper.map(regDTO, User.class)).thenReturn(user);
		Response response = userService.register(regDTO);
		assertEquals(200, response.getStatusCode());
	}

}
