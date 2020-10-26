package com.kutylo.testtask.service;


import com.kutylo.testtask.dto.request.AuthenticationRequest;
import com.kutylo.testtask.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
