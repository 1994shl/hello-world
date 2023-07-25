package com.shl.ssa.shop.product.test;

import lombok.Data;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author 施海林
 * @create 2023-06-08 13:55
 * @Description
 */
@Data
@Root(name = "Effect_Config")
public class SynRegionInfo {

    @ElementList(name = "Item", inline = true, required = false)
    List<RegionItem> itemList;
}
