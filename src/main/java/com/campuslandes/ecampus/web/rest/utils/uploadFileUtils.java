package com.campuslandes.ecampus.web.rest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import com.campuslandes.ecampus.security.SecurityUtils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class uploadFileUtils {

    private static String UPLOADED_FOLDER = "/Jhipster/file/upload/";

    public static ResponseEntity<String> uploadOneImage(MultipartFile file, String classe) {
		String contentType = file.getContentType();
        if (isNotImage(contentType)) {
            return ResponseEntity.badRequest().body("BAD TYPE");
        }

        String name = file.getOriginalFilename();
        String[] fileNameSplit = name.split(Pattern.quote("."));
        String fileName = "";
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("NO FILE");
        }
        for (String str : fileNameSplit)
            fileName += str + ".";
        name = SecurityUtils.getCurrentUserLogin().get();
        String shaName = DigestUtils.sha256Hex(fileName) + "." + fileNameSplit[fileNameSplit.length - 1];
        Path path;
        int x = 0;
        do {
            if (x > 0)
                shaName = DigestUtils.sha256Hex(fileName + x) + "." + fileNameSplit[fileNameSplit.length - 1];
            else
                shaName = DigestUtils.sha256Hex(fileName) + "." + fileNameSplit[fileNameSplit.length - 1];
            String url = UPLOADED_FOLDER + classe + "/" + name + "/" + shaName;
            path = Paths.get(url);
            x += randomUtils.getRandomNumberInRange(0, 10000);
        } while (Files.exists(path));
        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            if (!Files.exists(Paths.get(UPLOADED_FOLDER + classe + "/" + name + "/")))
                Files.createDirectories(Paths.get(UPLOADED_FOLDER + classe + "/" + name + "/"));
            if (!Files.exists(path)) {
                Files.write(path, bytes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(shaName);
    }

    private static boolean isNotImage(String contentType) {
        return !contentType.toLowerCase().trim().contains("image");
    }

    public static InputStream  getOneImage(String image, String classe)
            throws IOException {
        String[] fileNameSplit = image.split(Pattern.quote(":"));
        File initialFile = new File("");
        InputStream in = null;
        if (fileNameSplit.length >= 2) {
            try {
                initialFile = new File(UPLOADED_FOLDER + classe + "/" + fileNameSplit[0] + "/" + fileNameSplit[1]);
                in = new FileInputStream(initialFile);
            } catch (Exception e) {
                // TODO: handle exception
                in = null;
            }
        } else {
            in = null;
        }
        return in;
    }
}