package com.cisco.apix.controller;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ronshah on 8/6/15.
 */
@Controller
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/v1/hello", method = RequestMethod.GET)
    public
    @ResponseBody
    String Hello(@RequestParam(value = "json", required = false) boolean json,
                 @RequestParam(value = "headers", required = false) boolean headers,
                 @RequestParam(value = "log", required = false) boolean log,
                 HttpServletRequest httpServletRequest,
                 HttpServletResponse httpServletResponse) {

        String response, headersResponse = null;

        Map<String, String> headerMap = new HashMap<String, String>();

        if (headers) {
            Enumeration headerNames = httpServletRequest.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = httpServletRequest.getHeader(key);
                headerMap.put(key, value);
            }

            //get current date time with Calendar()
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            headersResponse = headerMap.toString();
            if (log) {
                LOGGER.info("Request received. Date/Time:"+dateFormat.format(cal.getTime())+". Headers"+headersResponse);
            }

        }

        if (headers && !(json)) {
            return "To view headers in response, you must submit json flag to true..";
        }

        if (log && !(headers)) {
            return "To log headers in log file, you must submit headers & json flag to true..";
        }

        try {

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            document.setXmlStandalone(true);

            Element root = document.createElement("helloResponse");
            document.appendChild(root);
            Element res = document.createElement("response");
            res.appendChild(document.createTextNode("Hello World!"));
            root.appendChild(res);

            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

            transformer.transform(domSource, result);

            response = writer.toString();

            if (json) {
                JSONObject object = XML.toJSONObject(response);
                if (headers) {
                    object.put("headers", headersResponse);
                }

                httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                return object.toString();
            } else {
                httpServletResponse.setContentType(MediaType.APPLICATION_XML_VALUE);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                return response;
            }

        } catch (Exception e) {
            httpServletResponse.setContentType(MediaType.TEXT_PLAIN_VALUE);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return e.getMessage();
        }

    } // End of Hello method

} // End of Class
