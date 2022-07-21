package com.example.calculator.web;

import com.example.calculator.service.CalculatorService;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public record CalculationApi (CalculatorService service) {
    @PostMapping(path = "/calculations")
    public CalculateExpressionResponse calculate(@RequestBody CalculateExpressionRequest request) {


        return new CalculateExpressionResponse (service.calculate(request.expression()));


    }
}

record CalculateExpressionRequest(String expression){}

record CalculateExpressionResponse(double result){}

