package com.test.xujixiao.xjx.protocol;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/10.
 * 县区数据
 */
@Table(name = "CountyProtocol")
public class CountyProtocol implements Serializable {
    @Column(name = "code",isId = true)
    public String code;
    @Column(name = "level")
    public int level;
    @Column(name = "name")
    public String name;
    @Column(name = "pCode")
    public String pCode;

}
