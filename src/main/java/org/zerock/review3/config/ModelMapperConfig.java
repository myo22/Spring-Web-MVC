package org.zerock.review3.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean //이 Bean은 그냥 등록이 아닌 해당 메소드의 실행 결과로 반환된 객체를 스프링의 빈(Bean)으로 등록시키는 역할을 합니다. (modelMapper)로 추정.
          //Configuration과 @Bean이 바로 XML설정 외에 자바 설정에서 많이 사용되는 설정입니다.
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) //null인 필드는 스킵
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //null인 필드는 스킵
                .setMatchingStrategy(MatchingStrategies.STRICT); //MatchingStrategies.STRICT를 사용하게 되면 기존 객체와 대상 객체의 필드명과 타입이 일치해야 맵핑을 시켜주도록 설정할 수 있습니다

        return modelMapper;
    }
}
