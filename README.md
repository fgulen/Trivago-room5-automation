# Cucumber BDD Test Automation Framework

Selenium & Java based `Frontend` and `Backend` automation suite utilising the BDD methodologies of Cucumber and Gherkin

## Prerequisites


-  JAVA SDK 12
-  Maven
-  chromedriver https://sites.google.com/a/chromium.org/chromedriver/downloads
-  Plugins (Cucumber,Gherkin)
   
    ### Mac
    
    Intellij IDEA   -> Preferences   -> Plugins ->  Marketplace -> `Cucumber` for Java
    
    Intellij IDEA   -> Preferences   -> Plugins ->  Marketplace -> `Gherkin`  
    
    ### Windows  
    Intellij IDEA   -> Settings     ->  Plugins -> Marketplace    -> `Cucumber` for Java
    
    Intellij IDEA   -> Preferences  -> Plugins  -> Marketplace    -> `Gherkin`  



## Framework Overview

The cucumber BDD testing framework Using keywords such as Given, When, Then and And, acceptance criteria tests known as feature files can then be broken down into testable steps.
Can be utilized both for UI and Backend Testing

### Cucumber Selenium
Overall testframework leveraging the Cucumber framework with Selenium written in JAVA.

### Feature File
The feature file specifies the steps in BDD language style (`Plain English Language`)

### Utilities Package
In order to keep common methods separate

### (POM)Page Object Model
Java class whereby the necessary HTML objects are captured as WebElements to be manipulated by the associated model class to be able to reach and maintain easly

### ChromeDriver.exe 
Local chromedriver necessary in order 

### Cucumber Reports
Cucumber has a built in report generation whereby Feature files tested are automatically written to cucumbers own reporting system.

To run Report
         mvn clean
         mvn verify => the folders and files will be created as HTML format

# Test cases in Gherkin format

```
  Feature: Mainmenu functions
  Background:
    Given user opens homepage

  Scenario Outline: Destination links go to page title
    When user clicks mainMenuIcon and clicks "destination" and "<destinationOptions>"
    Then verify that page title is "<pageTitle>"
    Examples:
      | destinationOptions | pageTitle |
      | Northwest          | Northwest |
      | Southwest          | Southwest |
      | Midwest            | Midwest   |
      | Southeast          | Southeast |
      | Northeast          | Northeast |


  Scenario:Destination menu has following datas
    When user clicks mainMenuIcon and clicks "destination"
    Then verify that menu exist following options
      | NORTHWEST |
      | SOUTHWEST |
      | MIDWEST   |
      | NORTHEAST |
      | SOUTHEAST |


  Scenario Outline:Search function
    When user clicks search button and writes "<destinations>" to search
    Then verify that message contains following "<destinations>"
    Examples:
      | destinations | texts          |
      | USA          | Search Results |
      | Ankara       | No results     |
      | Arizona      | Search Results |
      | Leverkusen   | No results     |
      | Istanbul     | Search Results |
  ```

```
Feature:Contact page sending message

  Background:
    Given user opens homepage

  Scenario Outline: With valid data
    Given user clicks "contacts" links to go contact page
    When user writes message in "<textArea>", name in "<fullName>" and your mail in "<yourEmail>" inputs
    Then Verify that user can see the "Message Sent Successfully!" message on the page

    Examples:
      | textArea | fullName | yourEmail |
      | message  | name     | email     |
      | message  | name     | email     |
```

```
@regression
Feature:Subscribe to the Newsletter

  Background:
    Given user opens homepage

  Scenario:Broken links verification
    Then verify the links

  Scenario Outline: Valid data and  subscription messages in different languages
    Given user selects "<country>" from drop-down menu
    When user writes own "e-mail" address to the newsletter subscription input and submits
    Then Verify that user gets  newsletter subscription "<message>" in the own language
    Examples:
      | country        | message                                                                                                                                                   |
      | USA            | You are now checked-in!                                                                                                                                   |
      | Canada         | We have sent you an e-mail to confirm your registration. If nothing is received, please also check your spam folder.                                      |
      | Australia      | You're now checked-in!                                                                                                                                    |
      | Deutschland    | Wir haben Dir eine E-Mail zum Bestätigen Deiner Anmeldung geschickt. Sollte nichts eingegangen sein, überprüfe bitte auch Deinen Spam-Ordner.             |
      | France         | Un e-mail de confirmation vous a été envoyé. Vous ne retrouvez pas votre e-mail de confirmation ? Merci de vérifier votre dossier “courrier indésirable”. |
      | Italia         | Ti abbiamo inviato una e-mail per confermare la tua registrazione. Se non viene ricevuto nulla, controlla anche la cartella dello spam.                   |
      | España         | Te hemos enviado un correo electrónico para confirmar tu registro. Si no lo has recibido, por favor comprueba tu carpeta de correo no deseado.            |
      | Suomi          | Olemme lähettäneet sinulle sähköpostitse pyynnön vahvistaa rekisteröitymisesi. Mikäli et ole saanut viestiä, tarkista myös roskapostikansiosi.            |
      | Brasil         | Obrigado por se inscrever a nossa newsletter                                                                                                              |
      | Portugal       | Acabamos de lhe enviar um e-mail para confirmar seu registro. Se não o recebeu, por favor, verifique também na sua pasta de spam.                         |
      | México         | Felicidades por unirte a trivago Magazine                                                                                                                 |
      | Ireland        | We have sent you an e-mail to confirm your registration. If nothing is received, please also check your spam folder.                                      |
      | United Kingdom | We have sent you an e-mail to confirm your registration. If nothing is received, please also check your spam folder.                                      |
      | Norge          | Vi har sendt deg en e-post med en bekreftelse på din påmelding. Hvis du ikke kan se e-posten, så ber vi deg sjekke søppelposten.                          |
      | Sverige        | Vi har skickat dig e-post som bekräftelse på din registrering. Om du inte kan hitta e-posten, ber vi dig att kolla din skräppost.                         |
      | Singapore      | Vi har skickat dig e-post som bekräftelse på din registrering. Om du inte kan hitta e-posten, ber vi dig att kolla din skräppost.                         |
      | Argentina      | ¡Felicitaciones por unirte a trivago Magazine!                                                                                                            |
      | Türkiye        | Abonelik kaydınızı tamamlamanız için bir e-posta gönderdik. Eğer e-postayı almadıysanız lütfen \\"Spam\\" bölümünü kontrol ediniz.                        |


  Scenario Outline: Invalid data and messages in different languages
    Given user selects "<country>" for invalid data
    When user writes "<invalid e-mail>" address to the newsletter subscription input and submits
    Then Verify that user gets newsletter subscription "<error message>" in own language
    Examples:

      | country        | invalid e-mail                | error message                                   |
      | USA            | email@111.222.333             | Please enter a valid e-mail address             |
      | Canada         | #@%^%#$@#$@#.com              | Please enter a valid email address              |
      | Australia      | Joe Smith <email@example.com> | Oops! Please enter a valid e-mail address :)    |
      | Deutschland    | içpüöçı@example.com           | Es ist ein Fehler aufgetreten                   |
      | France         | email@example@example.com     | Une erreur ? Entrez votre e-mail à nouveau      |
      | Italia         | .email@example.com            | Per favore, inserisci un indirizzo email valido |
      | España         | email.@example.com            | Por favor, introduce un e-mail válido           |
      | Suomi          | email..email@example.com      | Syötä voimassa oleva sähköpostiosoite           |
      | Türkiye        | içpüöçı@example.com           | Lütfen tekrar deneyin.                          |
      | Brasil         | email@example.com (Joe Smith) | Por favor, digite um e-mail válido.             |
      | Portugal       | email@111.222.333             | Por favor insira um e-mail válido               |
      | México         | email@-example.com            | Opción inválida                                 |
      | Ireland        | email@@example.web            | Please enter a valid e-mail address             |
      | United Kingdom | email@111.222.333.44444       | Please enter a valid email address              |
      | Norge          | email@example..com            | Vennligst oppgi en gyldig e-postadresse         |
      | Sverige        | Abc..123@example.com          | Vänligen uppge en giltig e-postadress           |
```
