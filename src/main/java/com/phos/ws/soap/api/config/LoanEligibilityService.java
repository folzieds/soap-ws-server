package com.phos.ws.soap.api.config;

import com.phos.ws.soap.api.loaneligibility.CustomerRequest;
import com.phos.ws.soap.api.loaneligibility.CustomerResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanEligibilityService {

    @Value("${customer.minimum.yearly.salary:840000}")
    private long salary;

    @Value(("${customer.minimum.score:500}"))
    private int score;

    @Value("${customer.minimum.age:18}")
    private int minimumAge;

    @Value("${customer.maximum.age:55}")
    private int maximumAge;

    public CustomerResponse checkLoanEligibility(CustomerRequest customerRequest){

        CustomerResponse customerResponse = new CustomerResponse();

        List<String> mismatchCriteria = customerResponse.getCriteriaMismatch();

        if(!(customerRequest.getAge() > minimumAge && customerRequest.getAge() <= maximumAge))
            mismatchCriteria.add("Applicant's age not in the eligible age bracket");

        if(!(customerRequest.getYearlyIncome() >= salary))
            mismatchCriteria.add("Minimum Gross salary should be 840000");

        if(!(customerRequest.getScore() >= score))
            mismatchCriteria.add("Low performance score, reapply after 3 months");

        if(mismatchCriteria.size() > 0) {
            customerResponse.setApprovedAmount(0);
            customerResponse.setEligible(false);
        }else{
            long amount = (long)(0.3 * salary);
            customerResponse.setApprovedAmount(amount);
            customerResponse.setEligible(true);
            mismatchCriteria.clear();
        }
        return customerResponse;
    }
}
