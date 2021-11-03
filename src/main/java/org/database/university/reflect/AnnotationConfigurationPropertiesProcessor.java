package org.database.university.reflect;

import org.database.university.configuration.DatabaseConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class AnnotationConfigurationPropertiesProcessor {

    public static void processConfigurationFile(String filename) {
        //load config file to RAM = read
        InputStream inputStream = AnnotationConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream))) {
            Map<String, String> configurationProperties = readProperties(fileReader);
            fillConfiguration(configurationProperties);
        } catch (IOException exc) {
            System.out.println("An error occurred during reading " + filename + " file.");
            throw new RuntimeException(exc);
        } catch (IllegalAccessException exc) {
            System.out.println("Couldn't set value");
            throw new RuntimeException(exc);
        }

        //

    }

    //parse lines to (String) Key-properties key/(Object) value - property value
    private static Map<String, String> readProperties(BufferedReader reader) throws IOException {
        Map<String, String> properties = new HashMap<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.isBlank()) {   //isBlank() = trim().isEmpty(); -> "   " -> true
                String[] elements = line.split("="); //'database.password=root".split("=") ->
                properties.put(
                        //database.connection,timeout -> connectiontimeout
                        elements[0].trim(), elements[1].trim());
            }
        }

        return properties;
    }


    //put key/value pairs to Map
    @SuppressWarnings("DuplicatedCode")
    private static void fillConfiguration(Map<String, String> properties) throws IllegalAccessException {
        //DatabaseConfiguration instance = DatabaseConfiguration.getInstance();

        Class<?> dbConfigurationClass = DatabaseConfiguration.class;

        Field[] fields = dbConfigurationClass.getDeclaredFields();
        for (Field field : fields) {

            Property annotation = field.getAnnotation(Property.class);
            if (annotation != null) {

                //if (Modifier.isStatic(field.getModifiers())){}

//            if (!field.getName().equalsIgnoreCase("instance")){
//                String lowerCaseFieldName= field.getName().toLowerCase(); //"connectionTimeout".toLowerCase() -> "connectiontimeout"
//                Object propertyValue = properties.get(lowerCaseFieldName);
                //}

                String key = annotation.key();     // annotation value
                String propertyValue = properties.get(key);

                field.setAccessible(true);
                field.set(DatabaseConfiguration.getInstance(), castStringToFieldType(field.getType(), propertyValue));
            }


        }

    }

    private static Object castStringToFieldType(Class<?> fieldType, String propertyValue) {
        if (fieldType == String.class) {
            return propertyValue;
        }

        if (fieldType.isPrimitive() && fieldType != boolean.class) {
            return propertyValue == null ? 0 : Integer.parseInt(propertyValue);
        }
        return null;
    }
}
