package idusw.springboot.boardkms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
//(exclude = DataSourceAutoConfiguration.class)
@EnableJpaAuditing //JPA Auditing 활성화
public class BoardKmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardKmsApplication.class, args);
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
