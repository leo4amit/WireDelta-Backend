package com.wiredelta.backend.exception;

import com.wiredelta.backend.errorCodes.WireDeltaError;



public class UserException extends WireDeltaException{

   public UserException(WireDeltaError error){
        super(error.name(),error.getErrorMessage());
    }

}
