package ar.ucc.edu.ArqSW.Parcial.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

}