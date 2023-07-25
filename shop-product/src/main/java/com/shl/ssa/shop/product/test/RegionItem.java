package com.shl.ssa.shop.product.test;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * @author 施海林
 * @create 2023-06-08 13:56
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Root(name = "Item")
public class RegionItem {

    @Attribute(name = "station_name", required = false)
    private String stationName;

    @Attribute(name = "station_id", required = false)
    private String stationId;

    @Attribute(name = "config_code", required = false)
    private String configCode;

    @Attribute(name = "enable", required = false)
    private String enable;

    @Attribute(name = "start_time", required = false)
    private String startTime;

    @Attribute(name = "end_time", required = false)
    private String endTime;

    @Attribute(name = "device_level", required = false)
    private String deviceLevel;

    @Attribute(name = "device_list", required = false)
    private String deviceList;

    @Attribute(name = "coordinate_pixel", required = false)
    private String coordinatePixel;
}
