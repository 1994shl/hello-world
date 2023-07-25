package com.shl.ssa.shop.product.util;

import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class XMLPaser
{
    public static final Logger LOG = LoggerFactory.getLogger(XMLPaser.class);

    public static String toXml(Object object)
    {
        if (null == object)
        {
            return "";
        }

        try
        {
            Serializer serializer = new Persister(new Format("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            serializer.write(object, byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        }
        catch (Exception e)
        {
            LOG.error("toXml fail.", e);
            return "";
        }
    }

    public static <T> T fromXml(String xmlString, Class<T> type)
    {
        if (null == xmlString)
        {
            return null;
        }
        try
        {
            return fromXml(xmlString.getBytes(StandardCharsets.UTF_8), type);
        }
        catch (Exception e)
        {
            LOG.error("fromXml fail.", e);
            return null;
        }
    }

    public static <T> T fromXml(byte[] xmlBytes, Class<T> type)
    {
        try
        {
            Serializer serializer = new Persister();
            ByteArrayInputStream is = new ByteArrayInputStream(xmlBytes);
            return serializer.read(type, is);
        }
        catch (Exception e)
        {
            LOG.error("fromXml fail.", e);
            return null;
        }
    }

    /**
     * 把字符串中的特殊字符用xml指定的字符代替
     *
     * @param str 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String changeStrToXml(Object str)
    {
        String tempStr = null == str ? "" : str.toString();
        // 转换所有&为&amp;
        tempStr = tempStr.replaceAll("[&]", "&amp;");
        // 转换所有<为&lt;
        tempStr = tempStr.replaceAll("[<]", "&lt;");
        // 转换所有>为&gt;
        tempStr = tempStr.replaceAll("[>]", "&gt;");
        // 转换所有‘为&apos;
        tempStr = tempStr.replaceAll("[']", "&apos;");
        // 转换所有"为&quot;
        tempStr = tempStr.replaceAll("[\"]", "&quot;");
        // 转换所有(为&#40;
        tempStr = tempStr.replaceAll("[(]", "&#40;");
        // 转换所有)为&#41;
        tempStr = tempStr.replaceAll("[)]", "&#41;");
        // 转换所有%为&#37;
        tempStr = tempStr.replaceAll("[%]", "&#37;");
        // 转换所有+为&#43;
        tempStr = tempStr.replaceAll("[+]", "&#43;");
        // 转换所有-为&#45;
        tempStr = tempStr.replaceAll("[-]", "&#45;");

        return tempStr;
    }

    /**
     * 把xml中的的特殊字符用转换回原始数据
     *
     * @param xmlStr 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String changeXmlToStr(String xmlStr)
    {
        if (StringUtils.isBlank(xmlStr))
        {
            return "";
        }

        // 转换所有&amp;为&
        xmlStr = xmlStr.replaceAll("&amp;", "&");
        // 转换所有&lt;为<
        xmlStr = xmlStr.replaceAll("&lt;", "<");
        // 转换所有&gt;为>
        xmlStr = xmlStr.replaceAll("&gt;", ">");
        // 转换所有&apos;为‘
        xmlStr = xmlStr.replaceAll("&apos;", "'");
        // 转换所有&quot;为"
        xmlStr = xmlStr.replaceAll("&quot;", "\"");
        // 转换所有&#40;为(
        xmlStr = xmlStr.replaceAll("&#40;", "(");
        // 转换所有&#41;为)
        xmlStr = xmlStr.replaceAll("&#41;", ")");
        // 转换所有&#37;为%
        xmlStr = xmlStr.replaceAll("&#37;", "%");
        // 转换所有&#43;为+
        xmlStr = xmlStr.replaceAll("&#43;", "+");
        // 转换所有&#45;为-
        xmlStr = xmlStr.replaceAll("&#45;", "-");

        return xmlStr;
    }

}
