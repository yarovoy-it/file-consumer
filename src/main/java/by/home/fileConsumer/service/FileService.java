package by.home.fileConsumer.service;

import by.home.fileConsumer.model.FileTransferModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${directory.path}")
    private String pathToDirectory;

    public void createWrite(List<FileTransferModel> listFile) {
        for (FileTransferModel currentFile : listFile) {
            createWriteFile(currentFile.getName(), currentFile.getBody());
        }
    }

    private boolean createWriteFile(String name, byte[] bFile) {
        Path path = Paths.get(pathToDirectory + name);
        try {
            Files.createFile(path);
            Files.write(path, bFile);
        } catch (IOException ex) {
            System.err.println("File already exists");
        }
        return false;
    }
}
