package com.phos.ws.soap.api.controller;


import com.phos.ws.soap.api.config.LoanEligibilityService;
import com.phos.ws.soap.api.loaneligibility.CustomerRequest;
import com.phos.ws.soap.api.loaneligibility.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class LoanEligibilityController {

    private static final String NAMESPACE="http://www.phos.com/ws/soap/api/loanEligibility";

    @Autowired
    private LoanEligibilityService loanEligibilityService;

    @PayloadRoot(namespace = NAMESPACE,localPart = "CustomerRequest")
    @ResponsePayload
    public CustomerResponse getLoanStatus(@RequestPayload CustomerRequest request){
        return loanEligibilityService.checkLoanEligibility(request);
    }
}
