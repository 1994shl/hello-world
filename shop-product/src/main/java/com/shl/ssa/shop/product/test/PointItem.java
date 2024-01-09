package com.shl.ssa.shop.product.test;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 施海林
 * @create 2023-06-08 10:49
 * @Description
 */
@Data
@Root(name = "Item")
public class PointItem {

    @Attribute(name = "station_name", required = false)
    private String stationName;

    @Attribute(name = "station_id", required = false)
    private String stationId;

    @Attribute(name = "area_id", required = false)
    private String areaId;

    @Attribute(name = "area_name", required = false)
    private String areaName;

    @Attribute(name = "device_id", required = false)
    private String deviceId;

    @Attribute(name = "device_name", required = false)
    private String deviceName;

    @Attribute(name = "component_id", required = false)
    private String componentId;

    @Attribute(name = "component_name", required = false)
    private String componentName;

    @Attribute(name = "bay_id", required = false)
    private String bayId;

    @Attribute(name = "bay_name", required = false)
    private String bayName;

    @Attribute(name = "main_device_id", required = false)
    private String mainDeviceId;

    @Attribute(name = "main_device_name", required = false)
    private String mainDeviceName;

    @Attribute(name = "device_type", required = false)
    private String deviceType;

    @Attribute(name = "meter_type", required = false)
    private String meterType;

    @Attribute(name = "appearance_type", required = false)
    private String appearanceType;

    @Attribute(name = "save_type_list", required = false)
    private String saveTypeList;

    @Attribute(name = "recognition_type_list", required = false)
    private String recognitionTypeList;

    @Attribute(name = "phase", required = false)
    private String phase;

    @Attribute(name = "device_info", required = false)
    private String deviceInfo;

    @Attribute(name = "data_type", required = false)
    private String dataType;

    @Attribute(name = "lower_value", required = false)
    private String lowerValue;

    @Attribute(name = "upper_value", required = false)
    private String upperValue;

    @Attribute(name = "video_pos", required = false)
    private String videoPos;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        /*list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("b");
        list.add("b");
        list.add("c");
        list.add("c");*/
        long aNum = list.stream().filter("a"::equals).count();
        long bNum = list.stream().filter("b"::equals).count();
        long cNum = list.stream().filter("c"::equals).count();
        long dNum = list.stream().filter("d"::equals).count();

        Long value = 0L;

        System.out.println(aNum);
        System.out.println(bNum);
        System.out.println(cNum);
        System.out.println(dNum);
        value = value + aNum;

        System.out.println(value);

    }
}
