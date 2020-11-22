package ar.ucc.edu.ArqSW.Parcial.jira.service;

import java.util.List;
import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ar.ucc.edu.ArqSW.Parcial.common.dto.ModelDtoConverter;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;
import ar.ucc.edu.ArqSW.Parcial.jira.dao.StateDao;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.StateRequestDto;
import ar.ucc.edu.ArqSW.Parcial.jira.dto.StateResponseDto;
import ar.ucc.edu.ArqSW.Parcial.jira.model.State;

@Service
@Transactional
public class StateService {

   @Autowired
	private StateDao stateDao;

    public StateResponseDto getStateById(Long id) throws EntityNotFoundException {
        State state = stateDao.load(id);
                
        StateResponseDto response = (StateResponseDto) new ModelDtoConverter().convertToDto(state, new StateResponseDto());	
        return response;
    }
	
	public List<StateResponseDto> getAllState() {
		List<State> states = stateDao.getAll();
		
		List<StateResponseDto> response = new ArrayList<StateResponseDto>();
		 
		for (State state : states) {
			response.add((StateResponseDto) new ModelDtoConverter().convertToDto(state, new StateResponseDto()));
		}
		
		return response;
	}
	
//	public StateResponseDto insertState(StateRequestDto request) {
//		
//		State state = (State) new ModelDtoConverter().convertToEntity(new State(), request);
//		
//		stateDao.insert(state);
//		
//		StateResponseDto response = (StateResponseDto) new ModelDtoConverter().convertToDto(state, new StateResponseDto());	
//		
//		return response;
//	}
}
