package com.nopcommerce.utils;

import com.nopcommerce.datareader.PropertyReader;

public class OSUtils {
    public enum OSType { WINDOWS, MAC, LINUX, OTHER}
    public static  OSType getcurrentOS() {
        String os = PropertyReader.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return OSType.WINDOWS;
        }
        if (os.contains("mac")) {
            return OSType.MAC;
        }
        if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            return OSType.LINUX;
        }
        return OSType.OTHER;
    }
}
