package com.ljx.common;

import lombok.Data;

import java.util.HashMap;

@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE = 10;
    private static int PAGE_NUM = 1;

    private int pageNum = PAGE_NUM;
    private int pageSize = PAGE_SIZE;

    private HashMap param;
}
