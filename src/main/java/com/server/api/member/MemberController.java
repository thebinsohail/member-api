package com.server.api.member;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


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
            member.setId(generateRandomUUID().toString());
            memberList.add(member);
        }
        return member;
    }


    @PostMapping(path = "/login")
    public Member login(@RequestBody LoginDto loginDto){

        Random random=new Random();
        Integer resetCode = Integer.valueOf(String.format("%04d", random.nextInt(10000)));
        Member loginMember = null;

        for (Member member:memberList) {
            if(member.getEmail().equals(loginDto.getEmail()) && member.getPassword().equals(loginDto.getPassword())){
                loginMember=member;
                loginMember.setResetCode(resetCode);
                break;
            }
            else
                throw new IllegalStateException(String.format("User with email %s not found!",loginDto.getEmail()));
        }
        return loginMember;
    }



    @GetMapping(path = "/all")
    public List<Member> getAllMembers(Member member){
        return memberList;
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteMember(@PathVariable("id") String id){


        for (Member member:memberList) {

            if(member.getId().equals(id)) {
                memberList.remove(member);
                return ResponseEntity.ok(String.format("Member with id %s was deleted!",id));
            }


        }

        return ResponseEntity.internalServerError().body("There was a problem deleting the member");

    }

    @PutMapping(path = "/update/{id}")
    public String updateMember(@PathVariable("id") String id,@RequestBody MemberDto memberDto){
        for (Member member:memberList) {

            if(member.getId().equals(id)) {

                member.setFullName(memberDto.getFullName());
                member.setRelation(memberDto.getRelation());
                member.setQrCode(memberDto.getQrCode());

                return String.format("Changes were done to member %s\n" +
                        memberDto.toString(),id);
            }


        }
            return "There was a problem";
    }

    // for the password updating
    public boolean memberExists=false;
    public Member member;

    @PutMapping(path = "/forgot/password/verify")
    public boolean forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto){

        for (Member member:memberList) {
                if(member.getEmail().equals(forgotPasswordDto.getEmail()) &&
                        member.getResetCode().equals(forgotPasswordDto.getResetCode())){
                      this.member=member;
                     memberExists=true;
                    return true;
                }}

           throw new IllegalStateException("User not found!");
    }


    @PutMapping(path = "/update/password")
    public String changePassword(@RequestBody UpdatePasswordDto updatePasswordDto){
        if(memberExists==true){
            member.setPassword(updatePasswordDto.getNewPassword());
            return "Password was changed successfully!";
        }
        else
            throw new IllegalStateException("There was a problem updating the password!");
    }

}










