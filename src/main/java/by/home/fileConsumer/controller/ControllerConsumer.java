package by.home.fileConsumer.controller;

import by.home.fileConsumer.dto.FileTransferDto;
import by.home.fileConsumer.model.FileTransferModel;
import by.home.fileConsumer.service.FileService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/download")
public class ControllerConsumer {

    private final FileService fileService;

    private final Mapper mapper;

    private final RestTemplate restTemplate;

    @Value("${url.server}")
    private String url;

    public ControllerConsumer(FileService fileService, Mapper mapper, RestTemplate restTemplate) {
        this.fileService = fileService;
        this.mapper = mapper;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/save")
    public void getFiles() {
        List<FileTransferDto> dtoList = this.exchangeAsList(url,
                new ParameterizedTypeReference<List<FileTransferDto>>() {
                });
        final List<FileTransferModel> fileDtoList = dtoList.stream()
                .map((fileModel) -> mapper.map(fileModel, FileTransferModel.class))
                .collect(Collectors.toList());
        fileService.createWrite(fileDtoList);

    }

    private <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }
}
