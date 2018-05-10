/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gearsofleo.wallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author johnson3yo
 */
@Controller
@ApiIgnore
public class RestClientController {

    @RequestMapping("/")
    public String home() {
        return "redirect:swagger-ui.html";
    }
}
