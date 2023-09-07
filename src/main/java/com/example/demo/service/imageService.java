package com.example.demo.service;

import com.sun.source.doctree.SeeTree;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author im_na
 */
@Service
public interface imageService {
    /**
     * @param path
     * @param image
     * @return
     */
    String uploadImage(String path, MultipartFile image);

    boolean checkFile(String fileName);

    InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
