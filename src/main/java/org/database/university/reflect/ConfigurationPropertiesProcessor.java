package org.database.university.reflect;

import org.database.university.configuration.DatabaseConfiguration;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ConfigurationPropertiesProcessor {

    public static void processConfigurationFile(String filename) {
        //load config file to RAM = read
        InputStream inputStream = ConfigurationPropertiesProcessor.class.getClassLoader().getResourceAsStream(filename);
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream))){
            Map<String, Object> configurationProperties = readProperties(fileReader);
            fillConfiguration(configurationProperties);
        }catch (IOException exc){
            System.out.println("An error occurred during reading " + filename + " file.");
            throw new RuntimeException(exc);
        }catch (IllegalAccessException exc){
            System.out.println("Couldn't set value");
            throw new RuntimeException(exc);
        }




        //put key/value pairs to Map

        //

    }

    //parse lines to (String) Key-properties key/(Object) value - property value
    private static Map<String, Object> readProperties(BufferedReader reader) throws IOException {
        Map<String, Object> properties = new HashMap<>();

        String line;
        while ( (line = reader.readLine()) != null){
            if(!line.isBlank()) {   //isBlank() = trim().isEmpty(); -> "   " -> true
                String[] elements = line.split("="); //'database.password=root".split("=") ->
                properties.put(
                        //database.connection,timeout -> connectiontimeout
                        elements[0].trim().replace("database", "").replace(".", ""),
                        elements[1].trim()
                );
            }
        }

        return properties;
    }

    private static void fillConfiguration(Map<String, Object> properties) throws IllegalAccessException {
        //DatabaseConfiguration instance = DatabaseConfiguration.getInstance();

        Class<?> dbConfigurationClass = DatabaseConfiguration.class;

        Field[] fields = dbConfigurationClass.getDeclaredFields();
        for (Field field : fields){

            //if (Modifier.isStatic(field.getModifiers())){}

            if (!field.getName().equalsIgnoreCase("instance")){
                String lowerCaseFieldName= field.getName().toLowerCase(); //"connectionTimeout".toLowerCase() -> "connectiontimeout"
                Object propertyValue = properties.get(lowerCaseFieldName);

                field.setAccessible(true); //allows setting a value to private field
                field.set(DatabaseConfiguration.getInstance(), castStringToFieldType(field.getType(), propertyValue));
            }

        }

    }

    private static Object castStringToFieldType(Class<?> fieldType, Object propertyValue){
        if (fieldType == String.class){
            return propertyValue;
        }

        if (fieldType.isPrimitive() && fieldType != boolean.class){
            return Integer.parseInt((String) propertyValue);
        }
        return null;
    }
}
