package com.cakir.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public final class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -3357578235086772559L;
}