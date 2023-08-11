package org.example;

import org.example.domain.Comment;
import org.example.domain.Location;
import org.example.domain.Reservation;
import org.example.domain.UserEntity;
import org.example.domain.lodging.Apartment;
import org.example.domain.lodging.Cabin;
import org.example.domain.lodging.House;
import org.example.domain.lodging.Lodging;
import org.example.repositories.*;
import org.example.services.ReservationService;
import org.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.InitializingBean;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Bootstrap implements InitializingBean {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LodgingRepository lodgingRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ClickLogRepository clickLogRepository;
    @Autowired
    private UserNeo4jRepository userNeo4jRepository;
    @Autowired
    private ReservationNeo4jRepository reservationNeo4jRepository;

    @Override
    public void afterPropertiesSet() throws Exception {

        System.out.println("************************************************************************");
        System.out.println("Running initialization");
        System.out.println("************************************************************************");
        init();
    }

    private void init(){


        lodgingRepository.deleteAll();
        clickLogRepository.deleteAll();
        userNeo4jRepository.deleteAll();
        reservationNeo4jRepository.deleteAll();

        String FOTO_BASE_USUARIO = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png";
        String FOTO_BASE = "https://cdn.mos.cms.futurecdn.net/7goCf343cXsWspUtZ4Vuh3.jpg";
        String LOREM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do";

        UserEntity juanPerez = new UserEntity(
                "Juan",
                "Perez",
                "Juan123",
                "JuanPe",
                LocalDate.parse("1980-01-01"),
                "Espania",
                100000.0,
                List.of(),
                FOTO_BASE_USUARIO
        );

        UserEntity josePerez = new UserEntity(
                "Jose",
                "Perez",
                "JoPe123",
                "JoPe",
                LocalDate.parse("1980-01-01"),
                "Kirguistan",
                100000.0,
                List.of(),
                FOTO_BASE_USUARIO
        );

        UserEntity pedroGarcia = new UserEntity(
                "Pedro",
                "Garcia",
                "pp333",
                "Pepe",
                LocalDate.parse("1995-01-01"),
                "Italia",
                90_000.0,
                List.of(juanPerez),
                FOTO_BASE_USUARIO
        );

        UserEntity lolaGomez = new UserEntity(
                "Dolores",
                "Gomez",
                "123Lola",
                "Lola",
                LocalDate.parse("1980-01-01"),
                "Peru",
                50_000.0,
                List.of(pedroGarcia, juanPerez),
                FOTO_BASE_USUARIO
        );

        UserEntity tomasSassaro = new UserEntity(
                "Tomas",
                "Sassaro",
                "1234567",
                "Sassa",
                LocalDate.parse("2001-01-11"),
                "Argentina",
                550_000.0,
                List.of(),
                FOTO_BASE_USUARIO
        );

        UserEntity fedePerini = new UserEntity(
                "Federico",
                "Perini",
                "Fede22",
                "Fede",
                LocalDate.parse("1998-09-14"),
                "Argentina",
                950_000.0,
                List.of(tomasSassaro, lolaGomez, pedroGarcia, juanPerez,josePerez),
                FOTO_BASE_USUARIO
        );

        Cabin cabania1 =
                new Cabin(
                        "Cabaña 1",
                        LOREM,
                        5,
                        2,
                        1,
                        LOREM,
                        List.of("Wifi", "Cochera"),
                        true,
                        20000.0F,
                        new Location("Argentina","Buenos Aires","Mar del Plata","Calle 48 3120"),
                        lolaGomez,
                        FOTO_BASE
                );
        Cabin cabania2 =
                new Cabin(
                        "Cabaña 2",
                        LOREM,
                        3,
                        4,
                        1,
                        LOREM,
                        List.of("Wifi"),
                        true,
                        15000.0F,
                        new Location("Burkina Faso","Passoré","Bokin","Rue 48 3120"),
                        tomasSassaro,
                        FOTO_BASE
                );

        Cabin cabania3 =
                new Cabin(
                        "Cabaña 3",
                        LOREM,
                        1,
                        1,
                        1,
                        LOREM,
                        List.of("Wifi"),
                        false,
                        1500.0F,
                        new Location("Antigua y Barbuda","St. John","St. John","Street 48 3120"),
                        lolaGomez,
                        FOTO_BASE
                );

        Cabin cabania4 =
                new Cabin(
                        "Cabaña 4",
                        LOREM,
                        3,
                        4,
                        1,
                        LOREM,
                        List.of("WIFI", "COCHERA"),
                        true,
                        15000.0F,
                        new Location("Venezuela","Caracas","Caracas","Calle 48 3120"),
                        lolaGomez,
                        FOTO_BASE
                );

        Cabin cabania5 =
                new Cabin(
                        "Cabaña 5",
                        LOREM,
                        3,
                        4,
                        1,
                        LOREM,
                        List.of("COCHERA"),
                        true,
                        15000.0f,
                        new Location("Papua nueva Guinea","Lae","Lae","jalan 48 3120"),
                        lolaGomez,
                        FOTO_BASE
                );

        Cabin cabania6 =
                new Cabin(
                        "Cabaña 6",
                        LOREM,
                        3,
                        4,
                        1,
                        LOREM,
                        List.of("PILETA"),
                        true,
                        15000.0f,
                        new Location("Dinamarca","Jutlandia","Odder","gade 48 3120"),
                        lolaGomez,
                        FOTO_BASE
                );

        Cabin cabania7 =
                new Cabin(
                        "Cabaña 7",
                        LOREM,
                        3,
                        4,
                        1,
                        LOREM,
                        List.of("WIFI"),
                        true,
                        15000.0f,
                        new Location("Finlandia","Savonia del Norte","Kuopio","katu 48 3120"),
                        juanPerez,
                        FOTO_BASE
                );

        Cabin cabania8 =
                new Cabin(
                        "Cabaña 8",
                        LOREM,
                        3,
                        4,
                        1,
                        LOREM,
                        List.of("COCHERA", "WIFI"),
                        true,
                        15000.0f,
                        new Location("Gabon","Haut-Ogooue","Franceville","rue 48 3120"),
                        juanPerez,
                        FOTO_BASE
                );

        Apartment depto1 =
                new Apartment(
                        "Departamento 1",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("COCHERA"),
                        false,
                        30000.0f,
                        new Location("Kirguistán","Chuy","Biskek","algo en ruso 48 3120"),
                        juanPerez,
                        FOTO_BASE
                );

        Apartment depto2 =
                new Apartment(
                        "Departamento 2",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("SPA"),
                        false,
                        30000.0f,
                        new Location("Turquia","Trebisonda","Trebisonda","Sokak 48 3120"),
                        juanPerez,
                        FOTO_BASE
                );

        Apartment depto3 =
                new Apartment(
                        "Departamento 3",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("GYM"),
                        false,
                        30000.0f,
                        new Location("Albania","Condado de Berat","Berat","rruge 48 3120"),
                        pedroGarcia,
                        FOTO_BASE
                );

        Apartment depto4 =
                new Apartment(
                        "Departamento 4",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("COCHERA"),
                        false,
                        30000.0f,
                        new Location("Guinea Bissau","Bissau","Bissau","rua 48 3120"),
                        pedroGarcia,
                        FOTO_BASE
                );

        Apartment depto5 =
                new Apartment(
                        "Departamento 5",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("GYM", "SPA"),
                        false,
                        30000.0f,
                        new Location("Croacia","Sisak-Moslavina","Sisak","ulica 48 3120"),
                        pedroGarcia,
                        FOTO_BASE
                );

        Apartment depto6 =
                new Apartment(
                        "Departamento 6",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("SPA", "COCHERA"),
                        false,
                        30000.0f,
                        new Location("Chile","Coquimbo","La Serena","Calle 48 3120"),
                        pedroGarcia,
                        FOTO_BASE
                );

        Apartment depto7 =
                new Apartment(
                        "Departamento 7",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("COCHERA", "GYM"),
                        false,
                        30000.0f,
                        new Location("Sierra Leona","Distrito de Bo","Bo","Street 48 3120"),
                        fedePerini,
                        FOTO_BASE
                );

        Apartment depto8 =
                new Apartment(
                        "Departamento 8",
                        LOREM,
                        3,
                        2,
                        1,
                        LOREM,
                        List.of("COCHERA", "GYM", "SPA"),
                        false,
                        30000.0f,
                        new Location("Republica Democratica del Congo","Tshopo","Tshopo","rue 48 3120"),
                        fedePerini,
                        FOTO_BASE
                );

        House casa1 = new House(
                "Casa 1",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("GYM", "SPA"),
                false,
                100000.0f,
                new Location("Zimbabue","Mashonalandia Occidental","Kadoma","rue 48 3120"),
                fedePerini,
                FOTO_BASE
        );

        House casa2 = new House(
                "Casa 2",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("SPA", "GYM"),
                false,
                100000.0f,
                new Location("Andorra","Andorra","Canillo","carrer 48 3120"),
                fedePerini,
                FOTO_BASE
        );

        House casa3 = new House(
                "Casa 3",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("COCHERA", "PILETA"),
                false,
                100000.0f,
                new Location("Sudafrica","Provincia del Cabo Septentrional","Kimberley","straat 48 3120"),
                lolaGomez,
                FOTO_BASE
        );

        House casa4 = new House(
                "Casa 4",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("PILETA", "COCHERA"),
                false,
                100000.0f,
                new Location("Singapur","Singapur","Singapur","Street 48 3120"),
                lolaGomez,
                FOTO_BASE
        );

        House casa5 = new House(
                "Casa 5",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("WIFI", "PILETA", "COCHERA"),
                false,
                100000.0f,
                new Location("Bosnia Y Herzegovina","Zenica-Doboj","Zenica","ulica 48 3120"),
                lolaGomez,
                FOTO_BASE
        );

        House casa6 = new House(
                "Casa 6",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("COCHERA", "WIFI"),
                false,
                100000.0f,
                new Location("Alemania","Schleswig-Holstein","Dithsmaschen","Strasse 48 3120"),
                tomasSassaro,
                FOTO_BASE
        );

        House casa7 = new House(
                "Casa 7",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("PILETA", "WIFI", "COCHERA"),
                false,
                100000.0f,
                new Location("Argentina","Buenos Aires","Buenos Aires","Calle 48 3120"),
                lolaGomez,
                FOTO_BASE
        );

        House casa8 = new House(
                "Casa 8",
                LOREM,
                1,
                2,
                3,
                LOREM,
                List.of("PILETA"),
                false,
                100000.0f,
                new Location("Noruega","Vestland","Bergen","gate 48 3120"),
                lolaGomez,
                FOTO_BASE
        );

        var lodgingList = List.of(
                cabania1, cabania2, cabania3, cabania4, cabania5, cabania6, cabania7, cabania8,
                casa1, casa2, casa3, casa4, casa5, casa6, casa7, casa8,
                depto1, depto2, depto3, depto4, depto5, depto6, depto7, depto8
        );

        userRepository.save(juanPerez);
        userRepository.save(pedroGarcia);
        userRepository.save(lolaGomez);
        userRepository.save(tomasSassaro);
        userRepository.save(josePerez);
        userRepository.save(fedePerini);

        List<String> persistedLodgings =  lodgingList.stream().map(it-> lodgingRepository.save(it).id).collect(Collectors.toList());

        Reservation reserva1 = new Reservation(LocalDate.parse("2023-08-10"), LocalDate.parse("2023-08-20"), juanPerez, persistedLodgings.get(0));
        Reservation reserva2 = new Reservation(LocalDate.parse("2023-08-10"), LocalDate.parse("2023-08-20"), juanPerez, persistedLodgings.get(1));
        Reservation reserva3 = new Reservation(LocalDate.parse("2023-08-21"), LocalDate.parse("2023-08-28"), pedroGarcia, persistedLodgings.get(2));
        Reservation reserva4 = new Reservation(LocalDate.parse("2023-07-10"), LocalDate.parse("2023-07-11"), pedroGarcia, persistedLodgings.get(3));
        Reservation reserva5 = new Reservation(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-07"), juanPerez, persistedLodgings.get(4));
        Reservation reserva6 = new Reservation(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-07"), juanPerez, persistedLodgings.get(5));
        Reservation reserva7 = new Reservation(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-07"), tomasSassaro, persistedLodgings.get(6));
        Reservation reserva8 = new Reservation(LocalDate.parse("2023-04-01"), LocalDate.parse("2023-04-07"), tomasSassaro, persistedLodgings.get(7));
        Reservation reserva9 = new Reservation(LocalDate.parse("2023-06-01"), LocalDate.parse("2023-06-07"), lolaGomez, persistedLodgings.get(8));
        Reservation reserva10 = new Reservation(LocalDate.parse("2023-06-01"), LocalDate.parse("2023-06-07"), lolaGomez, persistedLodgings.get(9));
        Reservation reserva11 = new Reservation(LocalDate.parse("2023-06-01"), LocalDate.parse("2023-06-07"), josePerez, persistedLodgings.get(10));
        Reservation reserva12 = new Reservation(LocalDate.parse("2023-06-01"), LocalDate.parse("2023-06-07"), josePerez, persistedLodgings.get(11));
        Reservation reserva13 = new Reservation(LocalDate.parse("2023-06-01"), LocalDate.parse("2023-06-07"), josePerez, persistedLodgings.get(12));
        Reservation reserva14 = new Reservation(LocalDate.parse("2023-06-01"), LocalDate.parse("2023-06-07"), josePerez, persistedLodgings.get(13));

        List<Reservation> reservationList = List.of(reserva1, reserva2, reserva3, reserva4, reserva5, reserva6, reserva7, reserva8,
                reserva9, reserva10,reserva11,reserva12,reserva13,reserva14);

        reservationRepository.saveAll(reservationList);

        Comment comentario = new Comment("Comentario Depto 1","Muy buena el depto, mas de lo esperado",5, fedePerini, persistedLodgings.get(4),LocalDate.now());

        commentRepository.save(comentario);

        userNeo4jRepository.save(UserService.convertUsuarioToUsuarioNeo4jDataObject(juanPerez));
        userNeo4jRepository.save(UserService.convertUsuarioToUsuarioNeo4jDataObject(pedroGarcia));
        userNeo4jRepository.save(UserService.convertUsuarioToUsuarioNeo4jDataObject(lolaGomez));
        userNeo4jRepository.save(UserService.convertUsuarioToUsuarioNeo4jDataObject(tomasSassaro));
        userNeo4jRepository.save(UserService.convertUsuarioToUsuarioNeo4jDataObject(fedePerini));
        userNeo4jRepository.save(UserService.convertUsuarioToUsuarioNeo4jDataObject(josePerez));

        reservationList.forEach(it -> reservationNeo4jRepository.save(ReservationService.convertReservaToReservaNeo4jDataObject(it)) );
}

}