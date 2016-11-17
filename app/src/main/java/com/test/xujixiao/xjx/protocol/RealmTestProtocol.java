package com.test.xujixiao.xjx.protocol;

import io.realm.RealmObject;

/**
 * Created by xujixiao on 2016/11/17.13:44
 * 邮箱：1107313740@qq.com
 * realm数据操作的实体类方法
 */

public class RealmTestProtocol extends RealmObject {
    /**
     * 1. @PrimaryKey：主键。可以设置到的类型有：String、byte、short、 int、long以及它们的封装类Byte, Short, Integer,Long。
     * 2. @Required：必填项，不能为null。
     * 3. @Ignore：忽视，数据库将不会存储该字段。
     * 4. @Index：索引,当设置了PrimaryKey后将默认设置@Index！添加索引后会导致增加变慢并且会导致数据库文件增大，但是查询的时候会快。
     */
    public String name;
    public int age;
}
