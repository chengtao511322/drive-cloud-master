package com.drive.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class JWTDemo04 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String jwtSecret="mayikt";
        // jwt jwtHeader
        JSONObject jwtHeader = new JSONObject();
        jwtHeader.put("alg","HS256");
        jwtHeader.put("typ","jwt");
        // jwt playload 
        JSONObject jwtPlayload = new JSONObject();
        jwtPlayload.put("userName","yushengjun644");
        jwtPlayload.put("age",22);
        //base64JwtHeader
        String base64JwtHeader= Base64.getEncoder().encodeToString(jwtHeader.toJSONString().getBytes());
        String base64JwtPlayload= Base64.getEncoder().encodeToString(jwtPlayload.toJSONString().getBytes());
        // 使用MD5 生成签名
        String signature = DigestUtils.md5Hex(jwtPlayload.toJSONString() + jwtSecret);
        String jwt=base64JwtHeader+"."+base64JwtPlayload+"."+signature;
        System.out.println(jwt);
        // 解密
        String jwtPlayloadStr=new String(Base64.getDecoder().decode(jwt.split("\\.")[1].getBytes()),
                "UTF-8");
        String jwtsignatureStr=jwt.split("\\.")[2];
        System.out.println(DigestUtils.md5Hex(jwtPlayloadStr+jwtSecret).equals(jwtsignatureStr));
      

    }
}