package com.wiredelta.backend.models.response;

import com.wiredelta.backend.models.error.Error;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse<T> implements Serializable {

    private T data;
    private Error error;

}
