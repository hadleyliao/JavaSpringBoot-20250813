package fcu.pbiecs.springdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name= "哈嘍世界", description = "提供簡單的 Hello World API")
@RestController // 處理HTTP請求的控制器
public class HelloWorldController {

    @Operation(summary = "Hello World", description = "")
    @GetMapping("/hello") // 把程式運行起來輸入http://localhost:8080/hello就看得到
    public String helloWorld() {
        return "Hello, World!";
    }

    @Operation(summary = "Hello World with name", description = "")
    @GetMapping("/hello/{name}")
    public String helloName(@PathVariable("name") String name){
        return "Hello, " + name + "!";
    }
}
