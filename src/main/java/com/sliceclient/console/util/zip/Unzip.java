package com.sliceclient.console.util.zip;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Found on google
 * @https://knpcode.com/java-programs/how-to-unzip-file-in-java/
 * **/
@Getter @Setter
@SuppressWarnings("all")
public class Unzip implements Runnable {

    private String path, zipPath;

    public Unzip(String zipPath, String path) {
        this.zipPath = zipPath;
        this.path = path;
    }

    public void run() {
        extract(zipPath, path);
    }

    private static void extract(String source, String dest){
        try {
            File root = new File(dest);
            if(!root.exists()){
                root.mkdir();
            }
            BufferedOutputStream bos = null;
            // zipped input

            FileInputStream fis = new FileInputStream(source);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {
                String fileName = entry.getName();
                File file = new File(dest + File.separator + fileName);

                if (!entry.isDirectory()) {
                    extractFileContentFromArchive(file, zis);
                } else{
                    if(!file.exists()){
                        file.mkdirs();
                    }
                }
                zis.closeEntry();
            }
            zis.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void extractFileContentFromArchive(File file, ZipInputStream zis) throws IOException{
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos, 4086);
        int len = 0;
        byte data[] = new byte[4086];
        while ((len = zis.read(data, 0, 4086)) != -1) {
            bos.write(data, 0, len);
        }
        bos.flush();
        bos.close();
    }
}
