package com.example.controller;

import com.example.domain.User;
import com.example.dto.FinalPriceDTO;
import com.example.dto.body.ConsumeProductsBody;
import com.example.dto.body.UserBody;
import com.example.exception.*;
import com.example.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Searches for the user with the informed id")
    @GetMapping()
    public ResponseEntity findUserById(@RequestParam(name = "id") Long id) {
        try {
            User user = this.userService.findUser(id);
            return ResponseEntity.ok(user);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(404).body(nfe);
        }
    }

    @ApiOperation("Adds a new user to the USER table, if it has a membership informed it also makes this user a member")
    @PostMapping("/newUser")
    public ResponseEntity addNewUser(@RequestBody UserBody body) {
        try {
            User user = this.userService.addNewUser(body);
            return ResponseEntity.ok(user);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(404).body(nfe.getMessage());
        } catch (MissingUsernameException mue) {
            return ResponseEntity.status(406).body(mue.getMessage());
        } catch (TakenUsernameException tue) {
            return ResponseEntity.status(409).body(tue.getMessage());
        }
    }

    @ApiOperation("Returns all the products that are in discount according to the user membership type")
    @GetMapping("/discountProducts")
    public ResponseEntity findDiscountProducts(@RequestParam(name = "username") String username) {
        try {
            return ResponseEntity.ok(this.userService.findAvailableDiscounts(username));
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(404).body(nfe.getMessage());
        } catch (MissingUsernameException mue) {
            return ResponseEntity.status(406).body(mue.getMessage());
        } catch (NoActiveMembershipException name) {
            return ResponseEntity.status(410).body(name.getMessage());
        }
    }

    @ApiOperation("Consumes the products informed in the body - returns the total price spent")
    @PostMapping("/buyProducts")
    public ResponseEntity consumeProducts(@RequestBody ConsumeProductsBody body) {
        try {
            FinalPriceDTO quantity = this.userService.consumeProducts(body);
            return ResponseEntity.ok(quantity);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(404).body(nfe.getMessage());
        } catch (MissingUsernameException mue) {
            return ResponseEntity.status(406).body(mue.getMessage());
        } catch (NoActiveMembershipException name) {
            return ResponseEntity.status(403).body(name.getMessage());
        } catch (UnavailableStockException use) {
            return ResponseEntity.status(410).body(use.getMessage());
        } catch (MissingRegisterException mre) {
            return ResponseEntity.status(501).body(mre.getMessage());
        }
    }
}
