package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserResponseDto getUserById(Long id) throws EntityNotFoundException, BadRequestException {
		
		if (id <= 0)
		{
			throw new BadRequestException();
		}
		User user = userDao.load(id);
		
		UserResponseDto response = new UserResponseDto();
				
		response.setId(user.getId());
		response.setName(user.getName());
		response.setLastname(user.getLastname());
		response.setEmail(user.getEmail());
		return response;
	}
	
	public List<UserResponseDto> getAllUser() {
		List<User> users = userDao.getAll();
		
		List<UserResponseDto> response = new ArrayList<UserResponseDto>();
		 
		for (User user : users) {
			response.add((UserResponseDto) new ModelDtoConverter().convertToDto(user, new UserResponseDto()));
		}
		
		return response;
	}
	
	public UserResponseDto insertUser(UserRequestDto request) throws BadRequestException {
		System.out.println("Antes del model");
		User user = (User) new ModelDtoConverter().convertToEntity(new User(), request);
		System.out.println("Desp del model");
		try {
		userDao.insert(user);
		System.out.println("Al final del try del service");
		}
		catch(BadRequestException e){
			System.out.println("En el catch del service");
			throw new BadRequestException();
		}
		System.out.println("Desp del insert");
		UserResponseDto response = (UserResponseDto) new ModelDtoConverter().convertToDto(user, new UserResponseDto());	
		
		return response;
		}
	}
