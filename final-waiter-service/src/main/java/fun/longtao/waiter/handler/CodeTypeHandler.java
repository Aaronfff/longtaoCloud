package fun.longtao.waiter.handler;

import fun.longtao.waiter.model.enums.OrderStatus;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeTypeHandler extends BaseTypeHandler<OrderStatus> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OrderStatus parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.ordinal());
    }

    @Override
    public OrderStatus getNullableResult(ResultSet rs, String columnName) throws SQLException {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.ordinal() == rs.getInt(columnName)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public OrderStatus getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public OrderStatus getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
