package com.task.purgefiles;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.task.model.Folders;

@Configuration
public class PurgeTaskConfig {

    @Bean
    public ItemReader<Folders> itemReader() {
        Jaxb2Marshaller foldersMarshaller = new Jaxb2Marshaller();
        foldersMarshaller.setClassesToBeBound(Folders.class);

        return new StaxEventItemReaderBuilder<Folders>()
                .name("configReader")
                .resource(new ClassPathResource("../resources/config.xml"))
                .addFragmentRootElements("folders")
                .unmarshaller(foldersMarshaller)
                .build();
    }
}