# JSON2XML
Streaming JSON to XML converter

Reads any JSON data and produces [XML Representation of JSON](https://www.w3.org/TR/xslt-30/#json-to-xml-mapping) specified in XSLT 3.0.

JSON2XML enables JSON transformation with XSLT even without having an XSLT 3.0 processor. You can simply pre-process the data by having JSON2XML before the transformation, and pipeline it into an XSLT 2.0 stylesheet, for example. That way your stylesheet stays forward compatible with XSLT 3.0, as the XML representation is exactly the same.

## Build

    mvn clean install

That should produce an executable JAR file `target/csv2rdf-1.0.0-SNAPSHOT-jar-with-dependencies.jar` in which dependency libraries will be included.

## Usage

The JSON data is read from `stdin`, UTF-8 encoding is expected. The resulting XML data is written to `stdout`.

    cat test.json | java -jar json2xml-1.0.0-SNAPSHOT-jar-with-dependencies.jar

Docker image is in the works.