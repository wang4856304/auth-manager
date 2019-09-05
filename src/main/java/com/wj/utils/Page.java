package com.wj.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author jun.wang
 * @title: Page
 * @projectName ownerpro
 * @description: TODO
 * @date 2019/9/5 11:34
 */

@Data
@NoArgsConstructor
public class Page<T> {

    private long total;
    private List<T> data;
    private int pageNo;
    private int pageSize;
}
