package by.home.fileConsumer.controller;

import by.home.fileConsumer.dto.FileTransferDto;
import by.home.fileConsumer.model.FileTransferModel;
import by.home.fileConsumer.service.FileService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControllerConsumer {

    private final FileService fileService;

    private final Mapper mapper;

    public ControllerConsumer(FileService fileService, Mapper mapper) {
        this.fileService = fileService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/{ping}", method = RequestMethod.GET)
    public ResponseEntity<String> ping(@PathVariable String ping) {
        return new ResponseEntity<>(ping, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.PUT)
    public void catchFiles(@RequestBody List<FileTransferDto> files) {
        List<FileTransferModel> fileDtoList = files.stream()
                .map((fileModel) -> mapper.map(fileModel, FileTransferModel.class))
                .collect(Collectors.toList());
        fileService.saveFile(fileDtoList);
    }

}
