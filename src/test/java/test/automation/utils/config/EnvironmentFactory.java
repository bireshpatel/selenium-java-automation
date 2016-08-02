package test.automation.utils.config;

import org.apache.commons.collections.CollectionUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
//import org.apache.log4j.Logger;

public class EnvironmentFactory {

    //private static Logger log = Logger.getLogger(EnvironmentFactory.class);

    private static Map<String, Map<String, String>> configMap = new HashMap<String, Map<String, String>>();

    static {
        XMLStreamReader xsr = null;
        InputStream paramInputStream = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(Environments.class);

            XMLInputFactory xif = XMLInputFactory.newInstance();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                    false);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            paramInputStream = EnvironmentFactory.class.getClassLoader()
                    .getResourceAsStream("config.xml");
            xsr = xif.createXMLStreamReader(paramInputStream);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Environments adaptedWrapper = (Environments) unmarshaller
                    .unmarshal(xsr);
            List<Environment> envs = adaptedWrapper.getEnvironments();

            for (Environment env : envs) {

                if (env != null) {

                    Set<Parameter> keyNValues = env.getParam();

                    Map<String, String> map = Collections.emptyMap();

                    if (CollectionUtils.isNotEmpty(keyNValues)) {

                        map = new HashMap<String, String>();

                        for (Parameter parameter : keyNValues) {

                            if (parameter != null) {
                                map.put(parameter.getKey(),
                                        parameter.getValue());
                            }

                        }
                    }

                    Object obj = configMap.put(env.getName(), map);

                    if (obj != null) {
                        throw new Exception(
                                "Duplicate environment name in metadata file");
                    }

                }

            }

        } catch (Exception e) {
            //log.error("Unable to convert environemt.xml to environemnt", e);
        } finally {
            if (xsr != null || paramInputStream != null) {
                try {
                    if (xsr != null) {
                        xsr.close();
                    }

                    if (paramInputStream != null) {
                        paramInputStream.close();
                    }
                } catch (XMLStreamException e) {
                    //log.error("Unable to close stream reader", e);
                } catch (IOException e) {
                }
            }
        }

    }

    public static final String getConfigValue(String key) /*throws Exception */{

        String env = System.getProperty("test.env");
        Map<String, String> sp = configMap.get(env);
        if (sp == null) {
//            throw new Exception("Not valid environment");
            assert false;
            return null;
        } else {
            return sp.get(key);
        }
    }

}
