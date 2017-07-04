## Introduction
Web Application Automation Framework using Selenium, Serenity, JBehave and Gradle

## Software & Tools
| Tools | Versions |
| ------ | ------ |
| Selenium | 2.45.0 |
| Java | 1.8.0_77 |
| Groovy | 2.4.4 |
| Firebug Plugin| Latest |
| Firepath Plugin | Latest |
| JBehave | 1.6.0 |
| Serenity | 1.0.47 |

## Running through Command Line:
Type the following to run the project through Command Line

`gradle clean test aggregate -Dstoryfilter=<storyname.story> or -Dmetafilter=+<metafilter> -Dtest.env=<environment> -i`

Type the following to run the project through IntelliJ Run Configuration
**Tasks:** `clean test aggregate`
**VM Options:** `-Dstoryfilter=<storyname.story> or -Dmetafilter=+<metafilter> -Dtest.env=<environment>`
