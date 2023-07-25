package com.shl.ssa.shop.product.test;


import com.shl.ssa.shop.product.util.XMLPaser;
import lombok.Data;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 施海林
 * @create 2023-06-08 10:39
 * @Description
 */
@Data
@Root(name = "Device_Model")
public class SynPointInfo {

    @ElementList(name = "Item", inline = true, required = false)
    List<PointItem> itemList;


    public static void beanToXml() {
        SynPointInfo info = new SynPointInfo();
        PointItem item = new PointItem();
        item.setAreaName("area Name");

        PointItem item1 = new PointItem();
        item1.setAreaName("area Name");

        List<PointItem> itemList = new ArrayList<>();
        itemList.add(item);
        itemList.add(item1);
        info.setItemList(itemList);
        System.out.println(XMLPaser.toXml(info));
    }

    public static void xmlToBean() {
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Device_Model>\n" +
                "   <Item area_name=\"area Name\"/>\n" +
                "   <Item area_name=\"area Name\"/>\n" +
                "</Device_Model>";
        SynPointInfo info = XMLPaser.fromXml(str, SynPointInfo.class);
        System.out.println(info.getItemList().get(0).getAreaName());
    }

    //将文件转换成Byte数组
    public static byte[] getBytesByFile(String pathStr) {
        File file = new File(pathStr);
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {


        //beanToXml();
        //xmlToBean();
        /*String path = "D:\\region.xml";
        SynRegionInfo info = XMLPaser.fromXml(getBytesByFile(path), SynRegionInfo.class);

        System.out.println(info);*/

        beanToXml();

    }
}

