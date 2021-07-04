package com.wiredelta.backend.exception;

import com.wiredelta.backend.errorCodes.WireDeltaError;
import lombok.Builder;
import lombok.Data;

public class JobsException extends WireDeltaException{

   public JobsException(WireDeltaError error){
        super(error.name(),error.getErrorMessage());
    }

}
