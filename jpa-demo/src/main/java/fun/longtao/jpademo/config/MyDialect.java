package fun.longtao.jpademo.config;

import org.hibernate.dialect.MySQL8Dialect;

public class MyDialect extends MySQL8Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
