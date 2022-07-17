package com.example.calculator.web;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationApi {

    @PostMapping(path = "/calculations")
    public CalculateExpressionResponse calculate(@RequestBody CalculateExpressionRequest request) {
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression = expressionParser.parseExpression(request.expression());
        double result = (Double) expression.getValue();

        return new CalculateExpressionResponse(result);


    }
}

record CalculateExpressionRequest(String expression){}

record CalculateExpressionResponse(double result){}

