package com.task.purgefiles;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.task.model.FoldersDTO;

@Configuration
public class PurgeTaskConfig {

    @Bean
    public ItemReader<FoldersDTO> itemReader() {
        Jaxb2Marshaller foldersMarshaller = new Jaxb2Marshaller();
        foldersMarshaller.setClassesToBeBound(FoldersDTO.class);

        return new StaxEventItemReaderBuilder<FoldersDTO>()
                .name("configReader")
                .resource(new ClassPathResource("../resources/config.xml"))
                .addFragmentRootElements("folders")
                .unmarshaller(foldersMarshaller)
                .build();
    }
}