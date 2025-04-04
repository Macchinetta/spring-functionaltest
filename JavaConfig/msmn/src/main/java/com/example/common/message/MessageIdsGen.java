/*
 * Copyright(c) 2025 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.common.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.ClassPathResource;

public class MessageIdsGen {

    public static void main(String[] args) throws IOException {
        // message properties file
        Class<?> targetClazz = MessageIds.class;
        Path output = Path.of("src/main/java", targetClazz.getName().replace(".", "/") + ".java");

        try (InputStream inputStream =
                new ClassPathResource("i18n/application-messages.properties").getInputStream();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter pw = new PrintWriter(Files.newBufferedWriter(output))) {

            pw.println("package " + targetClazz.getPackageName() + ";");
            pw.println("");
            pw.println("/**");
            pw.println(" * Message Id");
            pw.println(" */");
            pw.println("public class " + targetClazz.getSimpleName() + " {");

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                String[] vals = line.split("=", 2);
                if (vals.length > 1) {
                    String id = vals[0].trim();
                    String value = vals[1].trim();
                    pw.println("    /** " + id + "=" + value + " */");
                    pw.println("    public static final String "
                            + id.toUpperCase().replace(".", "_").replace("-", "_") + " = \"" + id
                            + "\";");
                }
            }
            pw.println("");
            pw.println("    private " + targetClazz.getSimpleName() + "() {}");
            pw.println("}");
        }
    }
}
