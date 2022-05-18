package com.dataart.apanch.service;

import com.dataart.apanch.model.AppPackage;
import com.dataart.apanch.repository.AppPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class AppPackageServiceImpl implements AppPackageService {
    @Autowired
    AppPackageRepository appPackageRepository;

    @Override
    public void save(AppPackage appPackage) {
        appPackageRepository.save(appPackage);
    }

    @Override
    public AppPackage findByAppId(Integer id) {
        return appPackageRepository.findByAppId(id);
    }

    @Override
    public void downloadPackage(Integer id, ServletOutputStream servletOutputStream) throws IOException {
        AppPackage appPackage = findByAppId(id);

        try (ZipOutputStream zipOut = new ZipOutputStream(servletOutputStream)) {
            Map<String, byte[]> map = new HashMap<>();
            map.put(appPackage.getFileName(), appPackage.getFile());
            if (appPackage.getSmallIconName() != null) {
                map.put(appPackage.getSmallIconName(), appPackage.getSmallIcon());
            }
            if (appPackage.getBigIconName() != null) {
                map.put(appPackage.getBigIconName(), appPackage.getBigIcon());
            }
            for (Map.Entry<String, byte[]> entry : map.entrySet()) {
                ZipEntry zipEntry = new ZipEntry(entry.getKey());
                zipOut.putNextEntry(zipEntry);
                zipOut.write(entry.getValue());
            }
        }
        appPackage.getApp().increaseDownloadsCount();
        save(appPackage);
    }
}
