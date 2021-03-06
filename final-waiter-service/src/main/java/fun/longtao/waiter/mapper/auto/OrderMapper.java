package fun.longtao.waiter.mapper.auto;

import fun.longtao.waiter.model.Order;
import fun.longtao.waiter.model.OrderExample;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

@Mapper
public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    long countByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    int deleteByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    @Delete({
            "delete from t_order",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    @Insert({
            "insert into t_order (create_time, update_time, ",
            "customer, waiter, ",
            "barista, discount, ",
            "total, ",
            "state)",
            "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
            "#{customer,jdbcType=VARCHAR}, #{waiter,jdbcType=VARCHAR}, ",
            "#{barista,jdbcType=VARCHAR}, #{discount,jdbcType=INTEGER}, ",
            "#{total,jdbcType=BIGINT,typeHandler=fun.longtao.waiter.handler.impl.MoneyTypeHandler}, ",
            "#{state,jdbcType=INTEGER,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler})"
    })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    int insertSelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    List<Order> selectByExampleWithRowbounds(OrderExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    List<Order> selectByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    @Select({
            "select",
            "id, create_time, update_time, customer, waiter, barista, discount, total, state",
            "from t_order",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("fun.longtao.waiter.mapper.auto.OrderMapper.BaseResultMap")
    Order selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Wed Sep 02 10:06:39 CST 2020
     */
    @Update({
            "update t_order",
            "set create_time = #{createTime,jdbcType=TIMESTAMP},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP},",
            "customer = #{customer,jdbcType=VARCHAR},",
            "waiter = #{waiter,jdbcType=VARCHAR},",
            "barista = #{barista,jdbcType=VARCHAR},",
            "discount = #{discount,jdbcType=INTEGER},",
            "total = #{total,jdbcType=BIGINT,typeHandler=fun.longtao.waiter.handler.impl.MoneyTypeHandler},",
            "state = #{state,jdbcType=INTEGER,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler}",
            "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Order record);
}