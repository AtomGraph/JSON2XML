/*
 * Copyright 2020 Martynas Jusevičius <martynas@atomgraph.com>.
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
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import javax.xml.stream.XMLStreamException;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Martynas Jusevičius <martynas@atomgraph.com>
 */
public class JsonStreamXMLWriterTest
{
    
    @Test
    public void testJsonKeyWithInvalidXMLChar() throws IOException, XMLStreamException
    {
        String jsonString = "{ \"a\\fb\": \"c\" }"; // form feed in the key (invalid in XML 1.0, valid in XML 1.1)
        InputStream json = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream xml = new ByteArrayOutputStream();
        
        try (Reader reader = new BufferedReader(new InputStreamReader(json, StandardCharsets.UTF_8)))
        {
            new JsonStreamXMLWriter(reader, new BufferedWriter(new OutputStreamWriter(xml, StandardCharsets.UTF_8))).
                    convert(StandardCharsets.UTF_8.name(), "1.0");
            String xmlString = xml.toString(StandardCharsets.UTF_8.name());
            assertTrue(xmlString.contains(JsonStreamXMLWriter.REPLACEMENT_CHAR)); // 
        }
    }
    
    @Test
    public void testJsonValueWithInvalidXMLChar() throws IOException, XMLStreamException
    {
        String jsonString = "{ \"a\": \"b\\fc\" }"; // form feed in the value (invalid in XML 1.0, valid in XML 1.1)
        InputStream json = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream xml = new ByteArrayOutputStream();
        
        try (Reader reader = new BufferedReader(new InputStreamReader(json, StandardCharsets.UTF_8)))
        {
            new JsonStreamXMLWriter(reader, new BufferedWriter(new OutputStreamWriter(xml, StandardCharsets.UTF_8))).
                    convert(StandardCharsets.UTF_8.name(), "1.0");
            String xmlString = xml.toString(StandardCharsets.UTF_8.name());
            assertTrue(xmlString.contains(JsonStreamXMLWriter.REPLACEMENT_CHAR)); // form feed has been replaced
        }
    }
    
}
