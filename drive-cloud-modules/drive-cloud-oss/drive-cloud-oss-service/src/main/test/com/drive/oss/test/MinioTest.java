/*
package com.drive.oss.test;

import io.minio.MinioClient;
import io.minio.ObjectStat;

public class MinioTest {

    public static void main(String[] args) {
        String bucketName = "pro";

        try {

            MinioClient minioClient = new MinioClient("http://125.0.8.191:9000", "JIA3V3GEZ606LM6PNEBX", "H8acR2RR3JLpLEb0ajfu/YIe8XgmcdkCsc+hk9hx");

            boolean isExist = minioClient.bucketExists(bucketName);

            if(isExist) {

                System.out.println("Bucket already exists");

            } else {

                minioClient.makeBucket(bucketName);

            }

            minioClient.putObject(bucketName,"newName_20180502092938.jpeg", "E://newName_20180502092938.jpeg");

            System.out.println("is successfully uploaded to `upload` bucket.");

            ObjectStat stat= minioClient.statObject(bucketName,"newName_20180502092938.jpeg");

            System.out.println(stat);

        } catch(Exception e) {

            System.out.println("Error occurred: " + e);

        }
    }
}
*/
