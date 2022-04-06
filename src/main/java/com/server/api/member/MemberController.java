package com.server.api.member;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(path = "/member")
public class MemberController {

    public static UUID generateRandomUUID(){
        UUID uuid=UUID.randomUUID();
        return uuid;
    }

    List<Member> memberList= new ArrayList<>();


    @PostMapping(path = "/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public Member saveMember(@RequestBody Member member){

        if(memberList.contains(member))
            throw new IllegalStateException("User already exists!");

        else {

            memberList.add(member);
        }
        return member;
    }


    @GetMapping(path = "/all")
    public List<Member> getAllMembers(Member member){
        return memberList;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") Integer id){


        for (Member member:memberList) {

            if(member.getId()==id) {
                memberList.remove(member);
                return ResponseEntity.ok(String.format("Member with id %d was deleted!",id));
            }


        }

        return ResponseEntity.internalServerError().body("There was a problem deleting the member");

    }

    @PutMapping(path = "/update/{id}")
    public String updateMember(@PathVariable("id") Integer id,@RequestBody MemberDto memberDto){
        for (Member member:memberList) {

            if(member.getId()==id) {

                member.setFullName(memberDto.getFullName());
                member.setRelation(memberDto.getRelation());
                member.setQrCode(memberDto.getQrCode());

                return String.format("Changes were done to member %d\n" +
                        memberDto.toString(),id);
            }


        }
            return "There was a problem";
    }


}










