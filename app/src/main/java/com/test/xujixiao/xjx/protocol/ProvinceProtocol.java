package com.test.xujixiao.xjx.protocol;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 * 省份数据
 */
@Table(name = "ProvinceProtocol")
public class ProvinceProtocol implements Serializable {
    @Column(name = "code",isId = true)
    public String code;
    @Column(name = "name")
    public String name;
    @Column(name = "level")
    public String level;
    @Column(name = "pCode")
    public String pCode;
    public List<TownProtocol> subArea;
    @Column(name = "yhdName")
    public String yhdName;
}
