package com.stay4it.sample.config;

/**
 * Created by ssyijiu on 2017/1/21.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 * <p>
 * SQL 语句相关
 */

public class SQL {

    public static final String SELECT_ALL_TABLE = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name;";
    private static final String SELECT_ALL_COL = "PRAGMA table_info([%s])";

    public static String getSqlSelectAllCol(String tableName) {
        return String.format(SELECT_ALL_COL, tableName);
    }
}
