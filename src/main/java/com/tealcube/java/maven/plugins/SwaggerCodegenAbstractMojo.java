package com.tealcube.java.maven.plugins;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.nio.charset.Charset;

public abstract class SwaggerCodegenAbstractMojo extends AbstractMojo {

    protected static final Charset UTF8 = Charset.forName("UTF-8");
    
    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "sourceDirectory", required = true)
    protected String inputSpec;

    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "outputDirectory", required = true, defaultValue = "${project.build.directory}/generated")
    protected File outputDirectory;
    
    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "language", required = false)
    protected String language;

    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "client", required = false)
    protected boolean client = true;

    @Parameter(property = SwaggerCodegenPlugin.PREFIX + "skip", required = false)
    protected boolean skip = false;

}
