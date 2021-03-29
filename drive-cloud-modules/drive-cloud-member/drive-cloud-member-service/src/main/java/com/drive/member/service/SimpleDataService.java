package com.drive.member.service;

import com.zhoyq.server.jt808.starter.dto.SimAuthDto;
import com.zhoyq.server.jt808.starter.entity.*;
import com.zhoyq.server.jt808.starter.service.DataService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author 小郭
 * @Description //TODO
 * @Date $ $
 * @Param $
 * @return $
 **/
@Component
public class SimpleDataService implements DataService {
    @Override
    public void terminalRsa(String sim, byte[] e, byte[] n) {

    }

    @Override
    public byte[] terminalRsa(String sim) {
        return new byte[0];
    }

    @Override
    public void terminalAnswer(String sim, int platformStreamNumber, String platformCommandId, String msgId, byte[] msgBody) {

    }

    @Override
    public void terminalHeartbeat(String sim) {

    }

    @Override
    public void terminalCancel(String sim) {

    }

    @Override
    public String terminalRegister(String sim, int province, int city, String manufacturer, String deviceType, String deviceId, byte licenseColor, String registerLicense) {
        return null;
    }

    @Override
    public void terminalLocation(String sim, LocationInfo locationInfo, Integer mediaId) {

    }

    @Override
    public void eventReport(String sim, byte eventReportAnswerId) {

    }

    @Override
    public void orderInfo(String sim, byte type) {

    }

    @Override
    public void cancelOrderInfo(String sim, byte type) {

    }

    @Override
    public void eBill(String sim, byte[] data) {

    }

    @Override
    public void driverInfo(String sim, DriverInfo driverInfo) {

    }

    @Override
    public void canData(String sim, CanDataInfo canDataInfo) {

    }

    @Override
    public void mediaInfo(String sim, MediaInfo mediaInfo) {

    }

    @Override
    public void mediaPackage(String sim, byte[] mediaData, Integer mediaId) {

    }

    @Override
    public void dataTransport(String sim, DataTransportInfo dataTransportInfo) {

    }

    @Override
    public void compressData(String sim, byte[] data) {

    }

    @Override
    public void terminalAuth(String phone, String authId, String imei, String softVersion) {

    }

    @Override
    public List<SimAuthDto> simAuth() {
        return null;
    }
}
