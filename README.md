# Monty Hall

Console application running simulations of the "Monty Hall Problem" (https://en.wikipedia.org/wiki/Monty_Hall_problem).

Will run a predetermined number of games using two different strategies, always keeping the initially selected box versus always changing box after the game show host reveals an empty box, and then comparing the results stating which strategy is more lucrative.

## Usage

### Prerequisites

* Maven
* Java 8

### Build
`mvn clean install`

### Run
`java -jar target/montyhall-0.0.1-SNAPSHOT.jar <arg>`

where `<arg>` is a single integer argument in range 10 to 1000000.
