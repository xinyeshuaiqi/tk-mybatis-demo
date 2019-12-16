package pers.wmx.tkmybatis;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TkMybatisApplication.class)
@MapperScan("pers.wmx.tkmybatis")
class TkMybatisApplicationTests {

    @Autowired
    PersonMapper personMapper;

    @Test
    void testInsert(){
        Person person = new Person();
        person.setName("haha");
        person.setAge(1000);

        PersonExtra personExtra = new PersonExtra();
        personExtra.setHeight(200);
        personExtra.setWeight(300); //kg
        personExtra.setNickname("hehe");
        person.setExtra(personExtra);
        personMapper.insertSelective(person);
    }

    @Test
    void testUpdate(){
        Person person = new Person();
        person.setAge(100);

        Example example = new Example(Person.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "a");

        personMapper.updateByExampleSelective(person,example);
    }


    @Test
    void testSelect(){
        Example example = new Example(Person.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", "niubi");
        List<Person> people = personMapper.selectByExample(example);
        System.out.println(people);
    }

}
