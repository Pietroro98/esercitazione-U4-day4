package PietroRomano;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

//        Faker faker = new Faker(Locale.ITALY);
//        String nome = faker.name().fullName();
//        String dataDiNascita = faker.date().birthday().toString();
//        String LuogoDiNascita = faker.address().city();
//
//        System.out.println("Nome: " + nome);
//        System.out.println("Data di Nascita: " + dataDiNascita);
//        System.out.println("Luogo di nascita: " + LuogoDiNascita);

        Supplier<Faker> fakerSupplier = () -> new Faker(Locale.ITALY);
        int numeroDiUtenti = 20;
        List<Utente> utenti = new ArrayList<>();


        for (int i = 0; i < numeroDiUtenti; i++) {
            LocalDate dataDiNascita = fakerSupplier.get().date().birthday(18, 80).toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            Utente utente = new Utente(
                    fakerSupplier.get().name().fullName(),
                    dataDiNascita,
                    fakerSupplier.get().address().city()
            );
            utenti.add(utente);
        }

        String dettagliUtenti = utenti.stream()
                .map(Utente::toString)
                .collect(Collectors.joining(", "));

        System.out.println("Dettagli degli utenti: " + dettagliUtenti);
        double average = utenti.stream().collect(Collectors.averagingInt(user -> user.getAge()));
        System.out.println("La media delle etá é : " + average);
        //------------------------------------------COLLECTORS--------------------------------------

        //Raggruppiamo gli utenti per Cittá e calcoliamo le medie delle etá per ognuna di esse:

        Map<String, Double> mediaEtaPerCitta = utenti.stream().collect(Collectors.groupingBy(user -> user.getLuogoDiNascita(), Collectors.averagingInt(user -> user.getAge())));
        mediaEtaPerCitta.forEach((citta, mediaEta) ->
                System.out.println("Città: " + citta + ", Età media: " + mediaEta));

        //Raggruppiamo per cittá e calcoliamo varie statistica come media eta, somma eta, eta minima e massima ecc..

        Map<String, IntSummaryStatistics> statistichePerCitta = utenti.stream().collect(Collectors.groupingBy(user -> user.getLuogoDiNascita(), Collectors.summarizingInt(user -> user.getAge())));
        statistichePerCitta.forEach((citta, statistiche) -> {
            System.out.println("Cittá: " + citta + ", Statistiche: " + statistiche);
        });
        //-----------------COMPARATORS------------------------------------------------------------
        System.out.println("---------");
        // da raggruppare, si puó ORDINARE!
        // 1. ordiniamo la lista utenti per etá(ordine crescente)
       List<Utente> userSortedByAge = utenti.stream().sorted(Comparator.comparing(user -> user.getAge())).toList();
       userSortedByAge.forEach(user -> System.out.println(user));
        System.out.println("---");

        // 2. ordiniamo la lista utenti per etá(ordine decrescente)
        // se si utilizza la reversed, mi sono prendere solo il nome classe, in questo caso utente :: getAge,
        //perché il reversed non fa usare la lambda.
        List<Utente> userSortedByAgeDesc = utenti.stream().sorted(Comparator.comparing(Utente::getAge).reversed()).toList();
        userSortedByAgeDesc.forEach(user -> System.out.println(user));

        // 1. ordiniamo la lista utenti per cognome(ordine crescente)
//        List<Utente> userSortedBySurname = utenti.stream()
//                .sorted(Comparator.comparing(Utente::getSurname))
//                .toList();
//        userSortedBySurname.forEach(user -> System.out.println(user));

        //'----------------------LIMIT-------------------------------
        System.out.println("LIMIT");
        // Opzionalmen te posso usare skip() per decidere anche di saltare tot elementi per ottenere ad esempio gli elementi dal 96esimo al100esimo.
        List<Utente> foveOfUtents = utenti.stream().sorted(Comparator.comparingInt(Utente::getAge).reversed()).skip(15).limit(5).toList();
        System.out.println(foveOfUtents);

        //------------------------------------Map To : -----------------------------------------------
        //1.Calcolo dell somma delle eta
       int totalAge = utenti.stream().map(Utente::getAge).reduce(0, (acc, age) -> acc + age);
        System.out.println("Somma di etá tramite reduce: " + totalAge);

        //2.Calcolo della somma delle eta tramite mapToInt
        int totalAge2 = utenti.stream().mapToInt(user -> user.getAge()).sum();
        System.out.println("Somma delle etá tramite mapToInt: " + totalAge2);

        //3.Calcolo della media delle etá tramite maptoInt
       OptionalDouble average2 = utenti.stream().mapToInt(user -> user.getAge()).average();
       if (average2.isPresent()) System.out.println("la media é :" + average2.getAsDouble());
       else System.out.println("Nessun utente presente per calcolare la media");

        //4.Calcolo della media delle etá maggiore tramite maptoInt
        OptionalInt maxAge = utenti.stream().mapToInt(user -> user.getAge()).max();
        if (maxAge.isPresent()) System.out.println("Létá massima é  : " + maxAge.getAsInt());
        else System.out.println("Nessun utente presente per calcolare la media");

        //5. ottenimento di statistiche sull'etá tramite mapToInt
        IntSummaryStatistics statistics = utenti.stream().mapToInt(user -> user.getAge()).summaryStatistics();
       System.out.println("La somma sull'etá ha statistica: " + statistics);
        System.out.println("Minima età: " + statistics.getMin());
        System.out.println("Massima età: " + statistics.getMax());


    }

    }

