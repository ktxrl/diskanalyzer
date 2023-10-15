package com.example.demo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DiskScanner {


    public long scanRecursively(File root) {
        long overallSizeInBytes = 0;
        for (File nestedElement : root.listFiles()) {
           if (nestedElement.isFile()) {
               overallSizeInBytes += nestedElement.length();
           } else { // we found a directory (folder)
               overallSizeInBytes += scanRecursively(nestedElement);
           }
        }

        return overallSizeInBytes;
    }

    public Map<String, Long> scan(File root) {
        Map<String, Long> fileNameToSize = new HashMap<>();

        File[] children = root.listFiles();
        for (File child : children) {
            if (child.isFile()) {
                fileNameToSize.put(child.getName(), child.length());
            }
        }
        return fileNameToSize;
    }

}
