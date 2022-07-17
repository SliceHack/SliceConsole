package com.sliceclient.console;

import com.sliceclient.console.util.download.DownloadUtil;
import com.sliceclient.console.util.zip.UnzipUtil;

import java.io.File;

/**
 * The main class for the program.
 * */
public class Start {

    /**
     * The main method of the Console
     * @param args The arguments of the launcher
     * */
    @SuppressWarnings("all")
    public static void main(String[] args) {
        System.setProperty("user.dir", System.getProperty("user.home") + File.separator + "Slice");
        String path = System.getProperty("user.home") + "//Slice//assets//launcher//background";

        File filePath = new File(System.getProperty("user.home") + File.separator + "Slice");

        File parent = new File(path).getParentFile(),
                file = new File(path),
                fileFont = new File(parent + File.separator + "font"),
                filelib = new File(filePath + File.separator + "lib"),
                fileZip = new File(filePath + File.separator + "lib.zip"),
                zip = new File(file + File.separator + "Background.zip"),
                zipFont = new File(parent + File.separator + "Poppins.zip"),
                ziplib = new File(filePath + File.separator + "lib.zip"),
                file1 = new File(file, "frame_000_delay-0.03s.png"),
                file2 = new File(fileFont, "Poppins-Regular.ttf"),
                file3 = new File(filelib, "1.8.jar"),
                ofl = new File(fileFont, "OFL.txt");

        if(!parent.exists()) parent.mkdirs();
        else if(!fileFont.exists()) fileFont.mkdirs();
        else if(!filelib.exists()) filelib.mkdirs();

        if(!file1.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Background.zip", zip.getAbsolutePath());
            UnzipUtil.unzip(zip.getAbsolutePath(), zip.getParentFile().getAbsolutePath());
        }

        if(!file2.exists()) {
            DownloadUtil.downloadFile("https://github.com/NickReset/SliceResources/raw/main/Poppins.zip", zipFont.getAbsolutePath());
            UnzipUtil.unzip(zipFont.getAbsolutePath(), fileFont.getAbsolutePath());
        }

        if(!file3.exists()) {
            DownloadUtil.downloadFile("https://api.sliceclient.com/download/lib", ziplib.getAbsolutePath());
            UnzipUtil.unzip(ziplib.getAbsolutePath(), filelib.getAbsolutePath());
        }


        if(zip.exists() && file1.exists()) zip.delete();
        if(zipFont.exists() && file2.exists()) zipFont.delete();
        if(ziplib.exists() && file3.exists()) ziplib.delete();
        if(ofl.exists()) ofl.delete();
        SliceConsole instance = SliceConsole.INSTANCE;
    }
}
