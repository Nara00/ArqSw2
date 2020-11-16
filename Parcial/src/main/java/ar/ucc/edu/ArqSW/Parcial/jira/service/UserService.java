package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.UserDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.UserResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.User;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserResponseDto getUserById(Long id){
		
		User user = userDao.load(id);
		
		UserResponseDto response = new UserResponseDto();
				
		response.setId(user.getId());
		response.setName(user.getName());
		response.setLastname(user.getLastname());
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
	
	public UserResponseDto insertUser(UserRequestDto request) {
		
		User user = (User) new ModelDtoConverter().convertToEntity(new User(), request);
		
		userDao.insert(user);
		
		UserResponseDto response = (UserResponseDto) new ModelDtoConverter().convertToDto(user, new UserResponseDto());	
		
		return response;
	}
}
