package com.tealcube.java.maven.plugins;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.codegen.CodegenConfig;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import java.io.File;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author AD82225
 * @version 29062015
 */
@Mojo(name = "client")
public class SwaggerCodegenClientMojo extends SwaggerCodegenAbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (inputSpec == null) {
            throw new MojoExecutionException("inputSpec must be defined!");
        }
        if (outputDirectory == null) {
            throw new MojoExecutionException("outputDirectory must be defined!");
        }
        if (language == null) {
            throw new MojoExecutionException("language must be defined!");
        }
        Swagger swagger = new SwaggerParser().read(inputSpec);

        CodegenConfig config = getLanguageConfig(language);
        config.setOutputDir(outputDirectory.getAbsolutePath());

        ClientOptInput input = new ClientOptInput().opts(new ClientOpts()).swagger(swagger);
        input.setConfig(config);
        List<File> output = new DefaultGenerator().opts(input).generate();
        for (File file : output) {
            getLog().info("Generating " + file.getAbsolutePath());
        }
    }

    private CodegenConfig getLanguageConfig(String name) {
        ServiceLoader<CodegenConfig> loader = ServiceLoader.load(CodegenConfig.class);
        for (CodegenConfig config : loader) {
            if (config.getName().equals(name)) {
                return config;
            }
        }

        try {
            return (CodegenConfig) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Can't load config class with name ".concat(name), e);
        }
    }
}
