package com.drive.test;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) {
        DateTime dateTime = DateUtil.offset(new Date(), DateField.DAY_OF_MONTH, 5);
        System.out.println(dateTime);
    }
}
