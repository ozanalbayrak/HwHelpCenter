package com.homeworkhelpcenter.demo.Common.Http;

import org.dozer.DozerBeanMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;

public class ResponseDto<T> extends HttpEntity<T> {

    private static final DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    private HttpStatus status;

    public ResponseDto(T body, HttpStatus status) {
        super(body);
        this.status = status;
    }

    public static ResponseDto.Builder accepted()
    {
        return status(HttpStatus.ACCEPTED);
    }
    public static Builder badRequest()
    {
        return status(HttpStatus.BAD_REQUEST);
    }
    public static Builder status(HttpStatus status)
    {
        return new BodyBuilder(status);
    }

    public interface Builder
    {

    }
    private static class BodyBuilder implements Builder
    {
        private HttpStatus status;
        private ModelMapper modelMapper = new ModelMapper();
        public BodyBuilder(HttpStatus status)
        {
            this.status = status;
        }
    }


}
