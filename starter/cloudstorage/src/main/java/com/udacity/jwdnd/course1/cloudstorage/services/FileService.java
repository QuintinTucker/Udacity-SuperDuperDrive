package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
public class FileService {
    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public FileMapper getFileMapper() {
        return fileMapper;
    }

    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void save(MultipartFile fileUpload, Authentication authentication) throws IOException {
        fileMapper.insertFile(
                new File(null,
                        fileUpload.getOriginalFilename(),
                        fileUpload.getContentType(),
                        Long.toString(fileUpload.getSize()),
                        userMapper.getUser(authentication.getName()).getUserId(),
                        fileUpload.getBytes()));
    }

    public List<String> getFileNames(){
        return fileMapper.getFileNames();
    }
}
