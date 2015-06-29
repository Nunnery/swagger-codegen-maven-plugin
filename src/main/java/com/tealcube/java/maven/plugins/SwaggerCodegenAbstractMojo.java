package com.tealcube.java.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.charset.Charset;

public abstract class SwaggerCodegenAbstractMojo {

    protected static final Charset UTF8 = Charset.forName("UTF-8");
    
    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "sourceDirectory", required = true, defaultValue = "${project.basedir}/src/docs/swagger")
    protected File sourceDirectory;

    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "outputDirectory", required = true, defaultValue = "${project.build.directory}/generated")
    protected File outputDirectory;
    
    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "language", required = true, defaultValue = "java")
    protected String language;

    @Parameter(property = SwaggerCodegen.PREFIX + "skip", required = false)
    protected boolean skip = false;

}
