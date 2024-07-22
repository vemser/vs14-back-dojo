package com.example.pessoa.exceptions;

import java.sql.SQLException;

public class BDException extends SQLException {
    public BDException(Throwable cause) {
        super(cause);
    }

    public BDException(String message) {
        super(message);
    }

    public BDException(String message, Throwable cause) {
        super(message, cause);
    }
}
