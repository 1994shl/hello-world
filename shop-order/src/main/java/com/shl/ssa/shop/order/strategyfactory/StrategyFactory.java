package com.shl.ssa.shop.order.strategyfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 施海林
 * @create 2023-02-15 15:22
 * @Description
 */
@Component
public class StrategyFactory {

    @Autowired
    private Map<String, Handler> strategyMap;

    @Autowired
    private List<Handler> handlerList;

    public Handler getHandler(String handlerName) {
        return strategyMap.get(handlerName);
    }

    public List<Handler> getHandlerList() {
        return handlerList;
    }

    public Map< String, Handler> getHandlerMap() {
        return strategyMap;
    }
}