package io.github.lgustavogomdam.api_rest_study_i.service.exceptions;

import java.io.Serializable;
import java.util.Date;

/*
* A classe ExceptionResponse é definida justamente para definir um corpo para a response, ou seja, quando uma response
* conter essa classe por causa de algum "throw", a resposta será um JSON com os atributos da ExceptionResponse.
*/

public class ExceptionResponse implements Serializable {

    public  static final long serialVersionUID = 1L;

    private Date timestamp;
    private String message;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }
}
