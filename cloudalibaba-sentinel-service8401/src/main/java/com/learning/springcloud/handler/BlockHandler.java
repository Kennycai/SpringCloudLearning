package com.learning.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {
    public static String dealTestHotKey(String p1, String p2, BlockException exception) {
        return "---------------BlockHandler.dealTestHotKey,o(╥﹏╥)o";
    }
}
