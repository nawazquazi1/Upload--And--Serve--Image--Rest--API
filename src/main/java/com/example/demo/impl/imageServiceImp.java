package com.example.demo.impl;

import com.example.demo.service.imageService;
import jakarta.activation.MimetypesFileTypeMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class imageServiceImp implements imageService {
    /**
     * @param path
     * @param image
     * @return
     */
    @Override
    public String uploadImage(String path, MultipartFile image) {
        String imageName = image.getOriginalFilename();
        String imagePath = path + File.separator + imageName;

        try {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            Files.copy(image.getInputStream(), Paths.get(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imageName;
    }

    /**
     * @param fileName
     * @return
     */
    @Override
    public boolean checkFile(String fileName) {
        File file = new File(fileName);
        String mimetype = null;
        try {
            mimetype = Files.probeContentType(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mimetype should be something like "image/png"
        return mimetype != null && mimetype.split("/")[0].equals("image");
    }

    /**
     * @param path
     * @param fileName
     * @return
     */
    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}

