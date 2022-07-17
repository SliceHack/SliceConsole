package com.sliceclient.console.util.zip;

import lombok.experimental.UtilityClass;

/**
 * Used for unzipping files.
 *
 * @author Nick
 * */
@UtilityClass
public class UnzipUtil {

    /**
     * Unzips a file.
     *
     * @param file - the file to unzip
     * @param destination - the destination to unzip to
     * */
    @SuppressWarnings("all")
    public static void unzip(String file, String destination) {
        new Unzip(file, destination).run();
    }
}
