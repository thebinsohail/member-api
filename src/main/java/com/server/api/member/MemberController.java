package com.server.api.member;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(path = "/member")
public class MemberController {

    public static UUID generateRandomUUID(){
        UUID uuid=UUID.randomUUID();
        return uuid;
    }

    List<Member> memberList=List.of(
            new Member(1L,"Anas Bin Sohail",generateRandomUUID().toString(),"Developer"),
            new Member(2L,"Qasim Khan ",generateRandomUUID().toString(),"Tester"),
            new Member(3L,"Muhammad Yousuf",generateRandomUUID().toString(),"Developer"),
            new Member(4L,"Siddique Ahmed",generateRandomUUID().toString(),"Manager")
            );

    @PostMapping(path = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public Member saveMember(@RequestBody Member member){

        if(memberList.contains(member))
            throw new IllegalStateException("User Already exists!");

        else
            memberList.add(member);

        return member;
    }

    @GetMapping(path = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Member> getAllMembers(Member member){
        return memberList;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteMember(@PathVariable String id){
        memberList.remove(id);
        return ResponseEntity.ok(String.format("User with id %s was deleted successfully!",id));
    }



}










