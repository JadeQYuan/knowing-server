package plus.knowing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("plus.knowing.dao")
public class KnowingApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowingApplication.class, args);
    }

}
