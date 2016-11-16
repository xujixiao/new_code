package com.test.xujixiao.xjx.protocol;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/10.
 * 市数据+
 */
@Table(name = "TownProtocol")
public class TownProtocol implements Serializable {
    @Column(name = "code",isId = true)
    public String code;
    @Column(name = "level")
    public int level;
    @Column(name = "name")
    public String name;
    @Column(name = "pCode")
    public String pCode;
    public List<CountyProtocol> subArea;

}
