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