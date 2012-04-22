package no.niths.application.rest.misc;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.niths.application.rest.RESTConstants;
import no.niths.application.rest.misc.interfaces.DiscoverabilityController;
import no.niths.common.constants.AppNames;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Simple discoverability class that handles GET requests to the ROOT uri
 * <p>
 * Returns all valid domain in header
 *
 */
@Controller
public class DiscoverabilityControllerImpl implements DiscoverabilityController{

    @Override
    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public String root(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType(RESTConstants.JSON);

        String json = buildURLs(req.getRequestURL().toString());

        System.err.println(json);
        
        return json;
    }

    private String buildURLs(String rootURL) {
        StringBuilder jsonBuilder = new StringBuilder("[");

        try {
            for (Field field : AppNames.class.getFields()) {

                jsonBuilder.append(String.format(
                        "{ \"url\": \"%s%s\"},",
                        rootURL, field.get(null)));
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return jsonBuilder.deleteCharAt(
                jsonBuilder.length() - 1).append(']').toString();
    }
}