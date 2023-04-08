package Data_Parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Annotation_Reflection {
    public static void main(String[] args) throws Exception {
        List<User> users = parse("C:\\Users\\TOPIKS\\IdeaProjects\\TestProject\\src\\data.in", User.class);
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static <T> List<T> parse(String path, Class<T> aClass) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<T> result = new ArrayList<>();
        Map<String, Field> fields = new HashMap<>();
        for (Field field : aClass.getDeclaredFields()) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                fields.put(column.value(), field);
            }
        }
        boolean isFirstLine = true;
        String[] columns = new String[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                if (isFirstLine) {
                    columns = split;
                    isFirstLine = false;
                } else {
                    Constructor<T> constructor = aClass.getConstructor();
                    T obj = constructor.newInstance();

                    for (int i = 0; i < split.length; i++) {
                        String value = split[i];
                        String column = columns[i];

                        Field field = fields.get(column);
                        String fieldName = field.getName();
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method method = aClass.getMethod(methodName, field.getType());

                        if (field.getType() == String.class) {
                            method.invoke(obj, value);
                        } else {
                            method.invoke(obj, Integer.valueOf(value));
                        }

                    }
                    result.add(obj);
                }

            }
        }
        return result;

    }
}
