package com.cis.batch33.library.controller;

import com.cis.batch33.library.entity.LibraryMember;
import com.cis.batch33.library.model.Member;
import com.cis.batch33.library.service.MemberService;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping
    public LibraryMember getMember(Integer memberId){
        return memberService.getMember(memberId);
    }

    // create a member
    @PostMapping
    public LibraryMember createMember(@RequestBody LibraryMember member){
        return memberService.createMember(member);
    }

    @PutMapping
    public LibraryMember updateMember(@RequestBody LibraryMember member){
        return memberService.updateMember(member);
    }

    @DeleteMapping
    public void deleteMember(Integer memberId){
         memberService.deleteMember(memberId);
    }
}
