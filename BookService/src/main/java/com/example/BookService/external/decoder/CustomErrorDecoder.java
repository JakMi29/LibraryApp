package com.example.BookService.external.decoder;


import com.example.RentalService.domain.RentalServiceCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        try (InputStream inputStream = response.body().asInputStream()) {
            String errorResponse = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));
            System.out.println(errorResponse);

            return new RentalServiceCustomException(
                    errorResponse,
                    HttpStatus.resolve(response.status()));

        } catch (IOException e) {
            throw new RentalServiceCustomException(
                    "Something gone wrong",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}