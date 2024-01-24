package com.example.datagenerator.mongoDB.generator;

import com.example.datagenerator.jpa.entity.Manager;
import com.example.datagenerator.jpa.entity.Statute;
import com.example.datagenerator.jpa.entity.User;
import com.example.datagenerator.jpa.repository.ManagerRepository;
import com.example.datagenerator.jpa.repository.StatuteRepository;
import com.example.datagenerator.jpa.repository.UserRepository;
import com.example.datagenerator.mongoDB.model.AdditionalRoomType;
import com.example.datagenerator.mongoDB.model.RoomType;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor
public class GeneratorHelper {

    protected final Faker faker;

    protected final List<String> userAdditionalInfo = List.of(
            "Uczulenie na orzechy",
            "Uczulenie na gluten",
            "Uczulenie na laktozę",
            "Wegetarianin",
            "Weganin",
            "Nietolerancja laktozy",
            "Dieta bezglutenowa",
            "Choroba trzewna",
            "Celiakia",
            "Dieta niskokaloryczna",
            "Dieta wysokobiałkowa",
            "Uczulenie na plesń",
            "Uczulenie na pyłki",
            "Choroba serca",
            "Choroba nerek",
            "Cukrzyca",
            "Weganin z opcją na jajka",
            "Wegańska dieta bezglutenowa",
            "Vegetarianin z opcją na ryby",
            "Czarnoskóry",
            "Dieta ketogeniczna"
    );
    protected final List<String> titles = List.of("Zaniedbanie", "Zgloszenie", "Naduzycie", "Halas");
    protected final List<String> addidtionalInformations = List.of("Szanowna Administracja Akademika,\n" +
            "\n" +
            "Zwracam się z prośbą o rozpatrzenie możliwości zmiany mojego obecnego pokoju. Z różnych powodów uważam, że przeniesienie do innego pomieszczenia byłoby dla mnie bardziej korzystne. Proszę o informacje dotyczące procedury i ewentualnych dostępnych opcji.", "Drodzy Pracownicy Administracji,\n" +
            "\n" +
            "Chciałbym uzyskać szczegółowe informacje na temat opłat związanych z zakwaterowaniem na nowy semestr. Czy istnieją jakieś nowe regulacje dotyczące opłat za mieszkanie? Proszę również o informacje dotyczące terminów płatności.", "Szanowni Państwo,\n" +
            "\n" +
            "Zwracam się z prośbą o przedłużenie mojego zakwaterowania o dodatkowy okres. Ze względu na okoliczności życiowe potrzebuję trochę więcej czasu na znalezienie nowego miejsca zamieszkania. Czy istnieje możliwość przedłużenia mojego obecnego kontraktu?", "Chciałbym zgłosić pewne kwestie dotyczące warunków mieszkania w moim obecnym pokoju. Niestety, napotykam na problemy z instalacją elektryczną i chciałbym poprosić o przegląd oraz ewentualne naprawy. Proszę o szybką reakcję w tej sprawie.", "Drodzy Pracownicy Administracji,\n" +
            "\n" +
            "Chciałbym zapytać, czy istnieje możliwość uzyskania dodatkowych udogodnień w moim pokoju, takich jak biurko czy regały. Obecnie zauważyłem brak pewnych elementów, które ułatwiłyby mi pracę nad studiami. Będę wdzięczny za rozważenie mojej prośby.", "Szanowne Biuro Administracji,\n" +
            "\n" +
            "Mam zamiar opuścić akademik po zakończeniu semestru i chciałbym uzyskać informacje na temat polityki zwrotu kaucji. Jakie są warunki zwrotu kaucji, i czy istnieją jakieś konkretne kroki, które powinienem podjąć w celu zabezpieczenia zwrotu kaucji?");


    protected final List<String> requirement_criteria = List.of(
            "Laureat olimpiady matematycznej, fizycznej lub informatycznej.",
            "Miejsce zameldowania powyżej 100 km od siedziby uczelni.",
            "Miejsce zameldowania powyżej 400 km od siedziby uczelni.",
            "Student konkretnego wydziału Informatyki i Telefomunikacji",
            "Osoba z niepełnosprawnością, wymagająca dostosowanych warunków zakwaterowania.",
            "Uczestnik programu wymiany międzynarodowej.",
            "Członek organizacji studenckiej zrzeszającej mieszkańców akademika.",
            "Absolwent szkoły partnerskiej uczelni, mający potwierdzone wyniki matury.",
            "Rodzina wielodzietna z dziećmi, które również studiują na uczelni.",
            "Pracownik uczelni lub pracownik administracyjny."
    );

   protected final List<String> contents = List.of("Ustawa_1", "Ustawa_2", "Ustawa_3", "Ustawa_4");
   protected final List<String> statute_info = List.of(
            "Zakaz palenia w pomieszczeniach akademika i na terenie kampusu.",
            "Obowiązkowa rejestracja gości w recepcji akademika oraz ograniczenie czasu ich pobytu.",
            "Zakaz hałasu po godzinie 22:00 w celu zapewnienia spokoju innym mieszkańcom.",
            "Zabrania się trzymania zwierząt domowych w pokojach, chyba że jest to wyraźnie zezwolone."
    );

   protected final Map<String,String> additionalService = Map.of("Dostęp do Internetu", "Szybki internet w cenie 30 zł/miesiąc."
           ,"Drukarka", "Drukarka dostępna na terenie akademika za 0,20 zł/strona."
           ,"Obiady", "Miejska stołówka oferuje obiady w cenie 12 zł/posiłek."
           ,"Pralnia", "Samoobsługowa pralnia dostępna za 5 zł/pranie."
           ,"Parking", "Parking dla mieszkańców w cenie 50 zł/miesiąc."
           ,"Siłownia", "Darmowy dostęp do siłowni na terenie akademika."
           ,"Serwis rowerowy", "Bezpłatny serwis i naprawa rowerów."
           ,"Bezpieczeństwo", "Całodobowa ochrona i monitoring."
           ,"Sala TV", "Sala z dostępem do kanałów telewizyjnych."
           ,"Serwis sprzątający", "Codzienny serwis ");

    protected final List<String> addRoomDescriptions = List.of(
            "Kuchnia wyposażona w sztućce, kubki i talerze oraz czajnik",
            "Sala kominkowa do spotkań i relaksu",
            "Sala fitness z nowoczesnym sprzętem do ćwiczeń",
            "Sala konferencyjna z miejscami siedzącymi dla 50 osób",
            "Sala gier z bilardem, ping-pongiem i grami planszowymi",
            "Pracownia komputerowa z dostępem do internetu",
            "Biblioteka z bogatym zbiorem książek i czasopism",
            "Pralnia samoobsługowa z pralkami i suszarkami",
            "Pomieszczenie do przechowywania rowerów",
            "Kawiarnia z kawą i przekąskami dla mieszkańców"
    );

    protected final List<String> floorDescriptions = List.of(
            "Przestronna przestrzeń z widokiem na kampus!",
            "Pokój z przytulnym dywanem i kolorowymi poduszkami.",
            "Minimalistyczny design sprzyjający nauce i relaksowi.",
            "Atmosfera sprzyjająca kreatywności i wymianie pomysłów.",
            "Nowoczesne wyposażenie i szybki dostęp do internetu.",
            "Pokój z ekologicznymi rozwiązaniami i naturalnym oświetleniem.",
            "Klimatyzacja dla komfortu w gorące dni.",
            "Przemyślana aranżacja, idealna do nauki i odpoczynku.",
            "Energia pozytywna w każdym kącie!",
            "Wygodne łóżko zapewniające regenerację po intensywnym dniu.",
            "Pokój z motywem roślinnym dla miłośników natury.",
            "Kącik do pracy zdalnej z ergonomicznym biurkiem.");

    protected final List<String> dormitoryDescriptions = List.of(
            "Ten akademik to nowoczesny budynek z pełnym wyposażeniem kuchni i łazienek.",
            "Ten akademik oferuje pokoje jednoosobowe i dwuosobowe z dostępem do internetu.",
            "Ten akademik znajduje się blisko kampusu i ma wspaniały widok na miasto.",
            "Ten akademik to doskonałe miejsce do nauki i spotkań towarzyskich.",
            "Ten akademik oferuje duże sale klubowe i siłownię dla studentów.",
            "Ten akademik jest przyjazny dla zwierząt i posiada teren rekreacyjny.",
            "Ten akademik to idealne miejsce dla miłośników sportu z boiskiem i salą fitness.",
            "Ten akademik zapewnia pełne wyżywienie i ma własną bibliotekę.",
            "Ten akademik jest dogodnie położony w centrum miasta z dostępem do komunikacji publicznej."
    );

    protected final List<String> dormitories =
            List.of("T2", "T3", "T4", "T6", "T15", "T16", "T17", "T18", "T19");


    protected boolean getFieldFillingProbability(int percentage) {
        return faker.number().numberBetween(0, 101) <= percentage;
    }

    protected User getRandomUserFromList(List<User> users) {
        if (users.isEmpty()) {
            return null;
        }

        int randomIndex = faker.number().numberBetween(0, users.size());
        return users.remove(randomIndex);
    }

    protected LocalDate getRandomDateLaterThen(LocalDate minDate) {
        return getRandomDateBetween(minDate, LocalDate.now());
    }

    protected LocalDate getRandomDateBetween(LocalDate minDate, LocalDate maxDate) {
        long minDay = minDate.toEpochDay();
        long maxDay = maxDate.toEpochDay();

        if (maxDay <= minDay + 1) {
            maxDay = minDay + 2;
        }
        long randomDay = ThreadLocalRandom.current().nextLong(minDay + 1, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    protected Long getSeniority(LocalDate maxDate) {
        return (long) faker.number().numberBetween(0, 2023 - maxDate.getYear() + 1);
    }


    protected String getQualifications() {
        List<String> qualifications = new ArrayList<>();
        for (int i = 0; i < faker.number().numberBetween(1, 5); i++) {
            qualifications.add(faker.job().keySkills());
        }
        return String.join(", ", qualifications);
    }

    protected String getWorkSchedule() {

        StringBuilder schedule = new StringBuilder();
        List<String> days = List.of("Pon", "Wt", "Sr", "Czw", "Pt", "Sob", "Nied");
        Set<Integer> randomDays = new HashSet<>();
        for (int i = 0; i < 7; i++) {
            randomDays.add(faker.number().numberBetween(0, 7));
        }
        randomDays.forEach(integer -> schedule.append(MessageFormat.format("{0}\n", getWorkingDay(days.get(integer)))));
        return schedule.toString();
    }

    private String getWorkingDay(String day) {
        int startWorkHour = faker.number().numberBetween(6, 11);
        int endWorkHour = faker.number().numberBetween(18, 24);
        return MessageFormat.format("{0}  {1}-{2}", day, startWorkHour, endWorkHour);
    }

    protected String getZipCode() {
        String zipCode = String.valueOf(faker.number().numberBetween(10, 100));
        zipCode += "-";
        zipCode += String.valueOf(faker.number().numberBetween(100, 1000));
        return zipCode;
    }

    protected String getHouseNumber() {
        int probability = faker.number().numberBetween(0, 10);
        String houseNumber = String.valueOf(faker.number().numberBetween(1, 150));
        if (probability == 3) {
            houseNumber += "A";
        } else if (probability == 7) {
            houseNumber += "B";
        }
        return houseNumber;
    }

    protected String getPhoneNumber() {
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            number.append(faker.number().numberBetween(0, 10));
        }
        return number.toString();
    }

    protected Manager getRandomManager(ManagerRepository managerRepository) {
        List<Manager> managers = managerRepository.findAll();
        if (managers.isEmpty()) {
            return null;
        }
        int randomIndex = faker.number().numberBetween(0, managers.size());
        return managers.get(randomIndex);
    }

    protected Statute getRandomStatute(StatuteRepository statuteRepository) {
        List<Statute> statutes = statuteRepository.findAll();
        if (statutes.isEmpty()) {
            return null;
        }
        int randomIndex = faker.number().numberBetween(0, statutes.size());
        return statutes.get(randomIndex);
    }

    protected AdditionalRoomType getRandomAdditionalRoomType(){

        AdditionalRoomType[] enumValues = AdditionalRoomType.values();
        int randomIndex = new Random().nextInt(enumValues.length);
        return enumValues[randomIndex];


    }

    protected RoomType getRandomRoomType(){

        RoomType[] enumValues = RoomType.values();
        int randomIndex = new Random().nextInt(enumValues.length);
        return enumValues[randomIndex];

    }

    protected Long getRandomNumberOfBeds() {
        return (long) faker.number().numberBetween(1, 5);
    }

    protected Long getRandomSize() {
        return (long) faker.number().numberBetween(10, 21);
    }

}
