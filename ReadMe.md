## Introduction
Web Application Automation Framework using Selenium, Apache CXF, Serenity, JBehave and Gradle

## Software & Tools
Selenium    : 2.45.0
Java        : 1.8.0_77
Apache CXF  : 3.1.0
Gradle      : 2.11
Groovy      : 2.4.4
Serenity    : 1.0.47
OS          : Windows 7 6.1 x86(32-bit)

## Running:
Type the following to run the project through Command Line
`gradle clean test aggregate -Dmetafilter=+<metafilter> -Dtest.env=<environment> -i`

Type the following to run the project through IntelliJ Run Configuration
Tasks: clean test aggregate
VM Options: -Dmetafilter=+<metafilter> -Dtest.env=<environment>
