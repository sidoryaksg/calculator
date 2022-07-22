package com.example.calculator.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationApi {
    @PostMapping(path="/operations")
    public CalculateOperationResponse calculate (@RequestBody CalculateOperationRequest request) throws IllegalAccessException {
        double result;

        switch (request.operator()) {
            case '+' -> result = request.x() + request.y();
            case '-' -> result = request.x() - request.y();
            case '*' -> result = request.x() * request.y();
            case '/' -> result = request.x() / request.y();

            default -> throw new IllegalAccessException("Unknown operator: %s ".formatted(request.operator()));
        }

        return new CalculateOperationResponse(result);

    }



}


record CalculateOperationRequest (double x, double y, char operator)
{

}

record CalculateOperationResponse(double result) {

}