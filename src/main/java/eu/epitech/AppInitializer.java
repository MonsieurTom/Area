package eu.epitech;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import eu.epitech.config.SpringRootConfig;

public class AppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { return new Class[]{SpringRootConfig.class};}

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }
}