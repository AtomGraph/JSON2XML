# JSON2XML
Streaming JSON to XML converter

Reads any JSON data and produces [XML Representation of JSON](https://www.w3.org/TR/xslt-30/#json-to-xml-mapping) specified in XSLT 3.0.

JSON2XML enables JSON transformation with XSLT even without having an XSLT 3.0 processor. You can simply pre-process the data by having JSON2XML before the transformation, and pipeline it into an XSLT 2.0 stylesheet, for example. That way your stylesheet stays forward compatible with XSLT 3.0, as the XML representation is exactly the same.

## Build

    mvn clean install

That should produce an executable JAR file `target/json2xml-1.0.0-SNAPSHOT-jar-with-dependencies.jar` in which dependency libraries will be included.

## Usage

The JSON data is read from `stdin`, UTF-8 encoding is expected. The resulting XML data is written to `stdout`.

JSON2XML is available as a `.jar` as well as a Docker image [atomgraph/json2xml](https://hub.docker.com/r/atomgraph/json2xml).

## Example

JSON data in `city-distances.json`:

```json
{
  "desc"    : "Distances between several cities, in kilometers.",
  "updated" : "2014-02-04T18:50:45",
  "uptodate": true,
  "author"  : null,
  "cities"  : {
    "Brussels": [
      {"to": "London",    "distance": 322},
      {"to": "Paris",     "distance": 265},
      {"to": "Amsterdam", "distance": 173}
    ],
    "London": [
      {"to": "Brussels",  "distance": 322},
      {"to": "Paris",     "distance": 344},
      {"to": "Amsterdam", "distance": 358}
    ],
    "Paris": [
      {"to": "Brussels",  "distance": 265},
      {"to": "London",    "distance": 344},
      {"to": "Amsterdam", "distance": 431}
    ],
    "Amsterdam": [
      {"to": "Brussels",  "distance": 173},
      {"to": "London",    "distance": 358},
      {"to": "Paris",     "distance": 431}
    ]
  }
}
```

Java execution from shell:

    cat city-distances.json | java -jar json2xml-1.0.1-SNAPSHOT-jar-with-dependencies.jar > city-distances.xml

Alternatively, Docker execution from shell:

    cat city-distances.json | docker run -i -a stdin -a stdout -a stderr atomgraph/json2xml > city-distances.xml

Note that you need to [bind](https://docs.docker.com/engine/reference/commandline/run/#attach-to-stdinstdoutstderr--a) `stdin`/`stdout`/`stderr` when running JSON2XML as a Docker container.

Output in `city-distances.xml` (indented for clarity):

```xml
<?xml version="1.0" ?>
<map xmlns="http://www.w3.org/2005/xpath-functions">
  <string key="desc">Distances between several cities, in kilometers.</string>
  <string key="updated">2014-02-04T18:50:45</string>
  <boolean key="uptodate">true</boolean>
  <null key="author"/>
  <map key="cities">
    <array key="Brussels">
      <map>
        <string key="to">London</string>
        <number key="distance">322</number>
      </map>
      <map>
        <string key="to">Paris</string>
        <number key="distance">265</number>
      </map>
      <map>
        <string key="to">Amsterdam</string>
        <number key="distance">173</number>
      </map>
    </array>
    <array key="London">
      <map>
        <string key="to">Brussels</string>
        <number key="distance">322</number>
      </map>
      <map>
        <string key="to">Paris</string>
        <number key="distance">344</number>
      </map>
      <map>
        <string key="to">Amsterdam</string>
        <number key="distance">358</number>
      </map>
    </array>
    <array key="Paris">
      <map>
        <string key="to">Brussels</string>
        <number key="distance">265</number>
      </map>
      <map>
        <string key="to">London</string>
        <number key="distance">344</number>
      </map>
      <map>
        <string key="to">Amsterdam</string>
        <number key="distance">431</number>
      </map>
    </array>
    <array key="Amsterdam">
      <map>
        <string key="to">Brussels</string>
        <number key="distance">173</number>
      </map>
      <map>
        <string key="to">London</string>
        <number key="distance">358</number>
      </map>
      <map>
        <string key="to">Paris</string>
        <number key="distance">431</number>
      </map>
    </array>
  </map>
</map>
```

## Dependencies

No third-party dependencies. Only Java packages [`javax.json`](https://docs.oracle.com/javaee/7/api/javax/json/package-summary.html) and [`javax.xml.stream`](https://docs.oracle.com/javase/8/docs/api/index.html?javax/xml/stream/package-summary.html) are used.
