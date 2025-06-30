package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImageController {
    @GetMapping("/getImage")
    public String getImage(Model model){
        File fileImg = new File("image/経路探索20251.png");
        try {
            byte[] byteImg = Files.readAllBytes(fileImg.toPath());
            String base64Data = Base64.getEncoder().encodeToString(byteImg);
            model.addAttribute("base64Data","data:image/png;base64,"+base64Data);
        }catch(IOException e) {
            return null;
        }
        return "image";
    }
}