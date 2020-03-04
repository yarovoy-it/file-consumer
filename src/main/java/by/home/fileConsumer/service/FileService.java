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

import static by.home.fileConsumer.component.Util.addString;
import static by.home.fileConsumer.component.Util.validate;

@Service
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${directory.path}")
    private String pathToDirectory;

    /**
     * Parsing list of FileTransferModel to name of file and body.
     * then createWriteFile saving
     *
     * @param listFile list of FileTransferModel
     */
    public void saveFile(List<FileTransferModel> listFile) {
        validate(listFile == null, "file.not.create");
        for (FileTransferModel currentFile : listFile) {
            createWriteFile(currentFile.getName(), currentFile.getBody());
        }
    }

    /**
     * Create files and filling up
     *
     * @param name  file name
     * @param bFile array of byte
     */
    private void createWriteFile(String name, byte[] bFile) {
        validate(pathToDirectory == null, "directory.not.found");
        validate(name == null, "file.not.create");
        validate(!Files.isDirectory(Paths.get(pathToDirectory)), "directory.not.found");
        try {
            Path path = Paths.get(pathToDirectory + name);
            if (Files.exists(path)) {
                long numberDuplicate = Files.list(path.getParent()).count();
                String refactorString = addString(name, String.valueOf(numberDuplicate), name.indexOf("."));
                Files.createFile(Paths.get(pathToDirectory + refactorString));
                Files.write(Paths.get(pathToDirectory + refactorString), bFile);
            } else {
                Files.createFile(path);
                Files.write(path, bFile);
            }
        } catch (IOException ex) {
            validate(true, "file.not.create");
            LOGGER.error(ex.getMessage());
        }
    }


}
