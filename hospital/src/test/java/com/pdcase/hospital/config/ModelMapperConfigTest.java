package com.pdcase.hospital.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ModelMapperConfigTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testModelMapperBean() {
        assertNotNull(modelMapper, "ModelMapper bean should not be null");
    }
}