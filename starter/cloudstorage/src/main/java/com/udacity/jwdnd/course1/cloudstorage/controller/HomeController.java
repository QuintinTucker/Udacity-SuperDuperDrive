package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller()
@RequestMapping("/home")
public class HomeController {
    private FileService fileService;

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping()
    public String homeView(Model model) {
        model.addAttribute("fileNames",fileService.getFileNames());
        return "home";
    }

    @PostMapping("/file-upload")
    public String getView(Authentication authentication, @RequestParam("fileUpload")
            MultipartFile fileUpload, Model model) throws IOException {
        fileService.save(fileUpload, authentication);
        return "home";
    }
}