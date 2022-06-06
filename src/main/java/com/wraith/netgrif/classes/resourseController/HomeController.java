package com.wraith.netgrif.classes.resourseController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController
{
    @GetMapping("/")
    public String homeController() { return "0xWraith - <a href=\"https://github.com/0xWraith\">GitHub</a>"; }
}
