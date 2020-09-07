package fun.longtao.multidatasourcedemo.conver;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MyResultSetExtractor implements ResultSetExtractor<List<Map<String, Object>>> {
    @Override
    public List<Map<String, Object>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        while (resultSet.next()) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", resultSet.getInt(1));
            map.put("bar", resultSet.getString(2));
            resultList.add(map);
        }
        return resultList;
    }
}
