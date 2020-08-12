package com.cakir.util;

import com.cakir.exceptions.BadRequestException;
import com.cakir.exceptions.ResourceNotFoundException;

public final class RestPreconditions{
	
	private RestPreconditions(){
		throw new AssertionError();
	}
	
	
	public static < T >T checkNotNull( final T reference ){
		if( reference == null ){
			throw new ResourceNotFoundException();
		}
		return reference;
	}
	
	
	public static < T >T checkRequestElementNotNull( final T reference ){
		if( reference == null ){
			throw new BadRequestException();
		}
		return reference;
	}
	
	public static void checkRequestState( final boolean expression ){
		if( !expression ){
			throw new BadRequestException();
		}
		
		
	}
	public static <T> T checkFound(T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException();
        }
        return resource;
    }

}
