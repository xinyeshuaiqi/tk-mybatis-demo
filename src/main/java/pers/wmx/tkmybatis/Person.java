package pers.wmx.tkmybatis;

import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wmx
 * @date 2019-12-06
 */
@Data
@Table(name = "person")
public class Person {

    @Id
    private Integer id;

    private String name;

    private Integer age;

    @ColumnType(jdbcType = JdbcType.LONGNVARCHAR)
    private PersonExtra extra;

}
