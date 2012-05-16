# NITHs
### Studentsamfunnet ved NITH sitt REST API.
##  [Mer informasjon](http://ec2-46-137-46-84.eu-west-1.compute.amazonaws.com/)

## Oppsett

### Krav
- Git
- Tomcat 7
- MySQL Server > 5
- JDK 1.7
- Maven
- Eclipse / Netbeans / IntelliJ IDEA

### Last ned kildekoden
>     `git clone git@github.com:niths/niths.git`

### Sett opp databasen
- Opprett en database kalt `niths`
    - Kjør følgende fra terminalen:` mysql -u <MySQL username> -e 'CREATE DATABASE niths;'`
    - Oppsøk andre manualer dersom nødvendig

- **Et alternativ er å bruke [MySQL Workbench](http://www.mysql.com/products/workbench/)**

### Konfigurer property filene
- Dersom du har blitt tildelt en **propert fil**, hopp over denne seksjonen
- Lag en ny fil under `src/main/resources` med navn `application.properties`
- Lim inn innholdet fra [denne gisten](https://gist.github.com/2226677), slik at de stemmer med ditt oppsett
- Lag en kopi av filen og gi kopien navnet `test-application.properties`, og plasser den under `niths/src/test/resources/`

### Bygg applikasjonen
- Applikasjonen kan bygges ved å kjøre `mvn clean install` fra prosjektroten fra kommandolinjen

### Deploer
- Plasser `niths/target/niths.war` i  `path/to/tomcat/installation/webapps/niths.war`
- Restart Tomcat: `service tomcat restart`
