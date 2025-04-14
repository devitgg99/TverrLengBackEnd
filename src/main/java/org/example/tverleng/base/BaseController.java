package org.example.tverleng.base;

import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<APIResponse> response() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity<APIResponse> response(APIResponse apiResponse) {
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}