package com.drive.oss.test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OssTest {

    public static void main(String[] args) throws IOException {
        Path currentDir = Paths.get("/home/jakobjenkov/myfile.txt");
        //Path workPath = currentDir.resolve("/");
        System.out.println(currentDir.toString().replace("\\", "/"));
    }
}
