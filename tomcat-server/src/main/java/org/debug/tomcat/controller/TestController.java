package org.debug.tomcat.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Thomas Li
 * @date 2025/2/20
 */
@RestController
public class TestController {
    @PostMapping("/debug")
    public String debug() {
        return "success";
    }
}
