package com.server.api.notify;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(path = "/status")
public class NotifyController {


    private static final int [] STATUS_CODE={100,200,304,401,404,500};

    @GetMapping
    public int subscribe(){

        int random=new Random().nextInt(STATUS_CODE.length);
        return STATUS_CODE[random];

    }

}
