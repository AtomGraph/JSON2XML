/*
 * Copyright 2019 Martynas Jusevičius <martynas@atomgraph.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.atomgraph.etl.json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javax.xml.stream.XMLStreamException;

/**
 * Main entry point to the conversion.
 * 
 * @author Martynas Jusevičius {@literal <martynas@atomgraph.com>}
 */
public class JSON2XML
{

    public static void main(String[] args) throws IOException, XMLStreamException
    {
        InputStream json = System.in;
        
        if (json.available() == 0)
        {
            System.err.println("JSON input: stdin");
            System.err.println("Example: cat sample.json | java -jar json2xml-jar-with-dependencies.jar > sample.xml");
            System.exit(1);
        }
        
        try (Reader reader = new BufferedReader(new InputStreamReader(json, StandardCharsets.UTF_8)))
        {
            new JsonStreamXMLWriter(reader, new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8))).
                convert(StandardCharsets.UTF_8.name(), "1.0");
        }
    }
    
}
