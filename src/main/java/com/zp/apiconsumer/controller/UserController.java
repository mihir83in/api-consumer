package com.zp.apiconsumer.controller;

import com.zp.apiconsumer.commons.model.web.Countries;
import com.zp.apiconsumer.commons.model.web.CurrencyUserRegistrationForm;
import com.zp.apiconsumer.converter.ModelConverter;
import com.zp.apiconsumer.security.UserPrincipal;
import com.zp.apiconsumer.services.CurrencyUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@Slf4j
public class UserController {


    private CurrencyUserService currencyUserService;
    private ModelConverter modelConverter;


    public UserController(CurrencyUserService currencyUserService, ModelConverter modelConverter) {
        this.currencyUserService = currencyUserService;
        this.modelConverter = modelConverter;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("currencyUser") CurrencyUserRegistrationForm currencyUser
            , BindingResult bindingResult, Model model) {

        log.trace("Handle Register Form: {}", currencyUser);

        if (bindingResult.hasErrors()) {
            addAttributes(model);
            return "registration";
        }

        currencyUserService.addUser(modelConverter.toCurrencyUser(currencyUser));
        log.debug("user added to db:", currencyUser);

        return "redirect:/login";
    }

    @GetMapping(value = "/register")
    public String registration(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {

        if (Optional.ofNullable(userPrincipal).isPresent()) {
            return "redirect:/convert";
        }

        model.addAttribute("currencyUser", new CurrencyUserRegistrationForm());
        addAttributes(model);
        return "registration";
    }

    private void addAttributes(Model model) {
        model.addAttribute("countries", Countries.getInstance().values());
    }
}
