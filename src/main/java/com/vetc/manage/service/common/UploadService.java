package com.vetc.manage.service.common;

import com.vetc.manage.exception.BusinessException;
import com.vetc.manage.utils.FileUtils;
import com.vetc.manage.utils.StringUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class UploadService {

  @Value("${server.image.dir.host}")
  private String serverImageDirImages = null;

  @Value("${server.image.path}")
  private String server_path_image = null;

  public List<String> uploadImage(Long memberId,  MultipartFile[] files) {
    List<String> lstRs = new ArrayList<>();
    try {
      memberId = (memberId != null) ? memberId : 0;
//      String subFolder = "";

//      if (StringUtil.isNullOrEmpty(type)) {
//        subFolder = "files/";
//      }

      for (MultipartFile file : files) {
        log.debug("file: " + file.getName());
        String fileName = FileUtils.uploadFile(serverImageDirImages, file, memberId);
        lstRs.add(server_path_image+ "/images/" + fileName);
      }
    } catch (Exception e) {
      log.error("---Up file fail..!---");
      log.error(ExceptionUtils.getMessage(e));
      throw new BusinessException("500", "Up load file fail..!");
    }
    log.info("----Up list file complete!------");
    return lstRs;
  }
}
