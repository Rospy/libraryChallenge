package com.disneychallenge.library.controller;

import com.disneychallenge.library.model.LoanModel;
import com.disneychallenge.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static java.util.Objects.nonNull;

@Controller
@RequestMapping(path = "apiv1/loans")
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanModel> registerLoan(@RequestBody LoanModel incomingObject) {
        LoanModel model = loanService.registerLoan(incomingObject);
        if(nonNull(model)) {
            return new ResponseEntity<>(model, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<LoanModel> updateLoan(@RequestBody LoanModel incomingObject) {
        LoanModel model = loanService.updateLoan(incomingObject);
        if(nonNull(model)) {
            return new ResponseEntity<>(model, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
