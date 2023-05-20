package com.lachgar.springproject.todoapi.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping(value = "/")
    public String greeting() {
        return "hi ,Welcom to spring boot";
    }

    @GetMapping(value = "/{name}")
    public String greetingWithName(@PathVariable String name) {
        return String.format("Welcom %s to our spring boot App.",name);
    }
}
