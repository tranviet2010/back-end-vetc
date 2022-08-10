package com.vetc.manage.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author HungVM
 */
@Slf4j
public class FileUtils {

  public static String readFile(String fileName) throws IOException {
    InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
    String strOut = IOUtils.toString(inputStream);
    if (StringUtils.isEmpty(strOut)) {
      throw new IOException("Unable to open file:" + fileName);
    }
    return strOut;
  }

  public static File getFile(String fileName) throws IOException {
    InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(fileName);
    File file = null;
    org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, file);

    return file;
  }

  public static String uploadFile(String path, MultipartFile file, Long memberId) throws Exception {
    String fileName = StringUtils
        .cleanPath(memberId + "-" + System.currentTimeMillis() + "-" + file.getOriginalFilename());
    fileName = fileName.trim().replaceAll(" ", "");
    File theDir = new File(path);
    if (!theDir.exists()){
      theDir.mkdirs();
    }
    Path targetLocation = Paths.get(path + fileName);
    // Files.createDirectories(targetLocation.getParent());
    Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
    log.debug("upload file: " + fileName + " complete !");
    return fileName;
  }
}
