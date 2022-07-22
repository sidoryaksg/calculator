package com.example.calculator.web.operation;

import com.example.calculator.web.operation.CalculateOperationRequest;
import com.example.calculator.web.operation.CalculateOperationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationAnApi {
    @PostMapping(path = "/an-operations")
    private CalculateOperationResponse calculate (@RequestBody CalculateOperationRequest request) {
        return new CalculateOperationResponse(request.x() + request.y());
    }

}
