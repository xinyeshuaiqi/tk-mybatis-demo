package pers.wmx.tkmybatis;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author wmx
 * @date 2019-12-16
 */
@Component
public class MybatisConfiguration {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        Configuration configuration = new Configuration();
        configuration.getTypeHandlerRegistry().register(PersonExtra.class,new GenericExtraTypeHandler<>(PersonExtra.class));
        factory.setConfiguration(configuration);
        return factory.getObject();
    }
}
