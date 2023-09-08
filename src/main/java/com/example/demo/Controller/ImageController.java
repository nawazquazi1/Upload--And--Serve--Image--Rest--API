package com.example.demo.Controller;

import com.example.demo.response.ImageResponse;
import com.example.demo.service.imageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author im_na
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private imageService imageService;

    @Value("${project.image}")
    private String path;

    /**
     * @param image
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<?> imageUpload(@RequestParam("image") MultipartFile image) {
        if (imageService.checkFile(image.getOriginalFilename())) {
            String imageName = this.imageService.uploadImage(path, image);
            return new ResponseEntity<>(imageName, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Upload Only Image");
    }

    /**
     * @param imageName
     * @param response
     */
    @GetMapping(value = "/download/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) {
        try {
            InputStream stream = this.imageService.getResource(path, imageName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            StreamUtils.copy(stream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
