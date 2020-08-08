package com.example.test;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    @RequestMapping("/ca/{val}")
    public String index(@PathVariable("val") String val) {
        System.out.println("###### " + val);
        return "G"+val;
    }

    @RequestMapping("/ca/sleep/{val}")
    public String indexTest(@PathVariable("val") String val) throws InterruptedException {
        System.out.println("###### with sleeep" + val);
        TimeUnit.SECONDS.sleep(Integer.valueOf(val));
        System.out.println("###### " + val);
        return val;
    }

    @RequestMapping("/ca/random/{val}")
    public String randomNumber(@PathVariable("val") String val) throws InterruptedException {
        Random r = new Random();
        int low = 0;
        int high = Integer.parseInt(val);
        int result = r.nextInt(high-low) + low;
        System.out.println("Random number " + result);
        return String.valueOf(result);
    }
}
