package com.wiredelta.backend.errorCodes;

import lombok.Getter;

@Getter
public enum WireDeltaError{


    WD_ERR_1001("Invalid UserName"),
    WD_ERR_1002("Invalid Job Proposal id"),
    WD_ERR_1003("Access Denied for the user"),
    WD_ERR_1004("Access Token is expired"),
    WD_ERR_1005("Invalid Token"),
    WD_ERR_1006("Validation Error"),
    WD_ERR_1007("Invalid Password"),


    WD_ERR_500("Some thing went wrong")


    ;

    private String errorMessage;

    WireDeltaError(String errorMessage){
        this.errorMessage=errorMessage;
    }

}
