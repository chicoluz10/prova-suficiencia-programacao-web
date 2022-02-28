package com.example.controller;

import com.example.domain.Member;
import com.example.dto.body.ChangeMemberStatusDTO;
import com.example.dto.body.MemberBody;
import com.example.exception.NotFoundException;
import com.example.exception.UnknownStatusException;
import com.example.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@Validated
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation("Turns a user into a member")
    @PostMapping("/addMember")
    public ResponseEntity addNewMember(@RequestBody MemberBody body) {
        try {
            Member member = this.memberService.addNewMember(body.getUserId(), body.getMembershipType());
            return ResponseEntity.ok(member);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(404).body(nfe);
        }
    }

    @ApiOperation("Updates the current status of the user's membership")
    @PostMapping("/changeStatus")
    public ResponseEntity changeMemberStatus(@RequestBody ChangeMemberStatusDTO body) {
        try {
            Member member = this.memberService.changeMemberStatus(body);
            return ResponseEntity.ok(member);
        } catch (NotFoundException nfe) {
            return ResponseEntity.status(500).body(nfe);
        } catch (UnknownStatusException use) {
            return ResponseEntity.status(406).body(use);
        }
    }
}
