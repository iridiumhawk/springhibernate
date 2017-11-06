package com.cherkasov;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {Config.class, HibernateConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles("dev")
//@Sql(scripts = {"classpath:db/hsql_schema.sql", "classpath:db/populateDB.sql"}, config = @SqlConfig(encoding = "UTF-8"))
public class TestWithSpring {

    @Autowired
    private Repository dataRepository;

    @Test
    public void test(){

//        Data data = dataRepository.get(1);
    }
}
