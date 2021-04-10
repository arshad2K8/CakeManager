package com.cake.manager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedName = loggedInUser == null ? "" : ((DefaultOAuth2User) loggedInUser.getPrincipal()).getAttribute("login");
        model.addAttribute("authenticatedName", authenticatedName);
        return "index";
    }
}
