package com.task.purgefiles;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.task.exception.ArquivoForaDasExtemsoesException;
import com.task.exception.ArquivoForaDosDiasException;
import com.task.model.Folder;
import com.task.model.Folders;

public class PurgeTask {
    private static final Logger logger = LoggerFactory.getLogger(PurgeTask.class);

    public static void run() {
        try {
            Folders folders = configuraXML();
            for (Folder folder : folders.getFolder()) {
                File pasta = new File(folder.getPath());
                File[] arquivos = pasta.listFiles();
                for (File arquivo : arquivos) {
                    validaArquivos(folder, arquivo);
                }

            }
        } catch (Exception e) {
            logger.error("Erro inesperado.");
            e.printStackTrace();
        }
    }

    private static void validaArquivos(Folder folder, File arquivo) throws URISyntaxException, IOException {
        logger.info("Validando arquivo:{} ", arquivo.getName());
        try {
            validaDiasParaExcluir(folder, arquivo);
            validaExtensaoArquivo(folder, arquivo);
            excluiArquivo(folder, arquivo);
        } catch (ArquivoForaDosDiasException e) {
            logger.info("arquivo fora dos dias.");
        } catch (ArquivoForaDasExtemsoesException e) {
            logger.info("arquivo fora das extensoes.");
        }
    }

    private static void excluiArquivo(Folder folder, File arquivo) throws URISyntaxException, IOException {
        logger.info("Excluindo arquivo: {}", arquivo.getName());
        arquivo.delete();
        if(!arquivo.exists()){
            String nome= arquivo.getName();
            geraLogDeArquivos(nome,folder.getName(), folder.getPath());
        }
    }

    private static void geraLogDeArquivos(String nome, String name, String path) throws URISyntaxException, IOException {
        new LocalDate();
        
        String nomeLog = name;
        File arquivoLog = new File(  "C:/Storage/logs", nomeLog+".log" );

        if(arquivoLog.exists()){
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLog , true));
            writer.append("Arquivo removido: "+ path + "/"+ nome +"\r\n"  );
            writer.flush();
            writer.close();
        }
        else{
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLog , true));
            writer.append("Arquivo removido: "+ path + "/"+ nome +"\r\n"  );
            writer.close();
            arquivoLog.createNewFile();
        }



    }

    private static void validaDiasParaExcluir(Folder folder, File arquivo) throws ArquivoForaDosDiasException {
        Integer diasExclusao = folder.getTimeToKeepFiles();
        DateTime dataArquivo = new DateTime(arquivo.lastModified());
        Days diferencaEntreDias = Days.daysBetween(dataArquivo, new DateTime());

        if ((diferencaEntreDias.getDays() < diasExclusao)) {
            throw new ArquivoForaDosDiasException();
        }
    }

    private static void validaExtensaoArquivo(Folder folder, File arquivo) throws ArquivoForaDasExtemsoesException {
        boolean arquivoForadasExtensoes = true;
        String extensaoArquivo = FilenameUtils.getExtension(arquivo.getName());
        if (folder.getExtensions().contains(",")) {
            String[] filesExtensions = folder.getExtensions().split(",");
            for (String extensao : filesExtensions) {
                if (extensao.equals(extensaoArquivo)) {
                    arquivoForadasExtensoes = false;
                }

            }
            
        } else {
            if (folder.getExtensions().equals(extensaoArquivo)) {
                arquivoForadasExtensoes = false;
            }
        }
        if(arquivoForadasExtensoes){throw new ArquivoForaDasExtemsoesException();}
    }

    private static Folders configuraXML() throws URISyntaxException, JAXBException {
        URL res = PurgeTask.class.getClassLoader().getResource("config.xml");
        File file = Paths.get(res.toURI()).toFile();
        JAXBContext jaxbContext = JAXBContext.newInstance(Folders.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Folders folders = (Folders) jaxbUnmarshaller.unmarshal(file);

        System.out.println(folders.getFolder().get(0).getName());
        return folders;
    }

}
