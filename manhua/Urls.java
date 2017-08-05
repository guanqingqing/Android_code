package com.andy.week1;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;


@Table(name = "week")
public class Urls {
    @Column(name = "id",isId = true)
    private int id;
    @Column(name = "name")
    public String name;
    @Column(name = "url")
    public String url;
}
