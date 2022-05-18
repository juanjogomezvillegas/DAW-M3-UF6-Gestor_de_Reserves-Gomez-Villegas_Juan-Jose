# DAW-M3-UF6-Gestor_de_Reserves-Gomez-Villegas_Juan-Jose

Gestor de Reserves d'un Hotel (programa CLI) fet amb Java, i fent servir Java (JDBC) per fer connexions a una base de dades de MySQL. L'Hotel té el nom 'Hotel ABP' (inventat). El Gestor permet realitzar una de les següents operacions:

- Afegir un Client.
- Crear una Reserva.



El Gestor de Reserves és una tasca de la UF6 del mòdul de programació de 2DAW.

# Descripció de l'Activitat

## 1. Gestor d’hotels Hooking

Tothom coneix Booking.com, és una utilitat que ens permet realitzar reserves d’habitacions a hotels de tot el mon.

En aquest cas, farem el nostre propi gestor de reserves.
Per poder-ho fer tenim una base de dades MySQL on consta la informació referent als hotels, les seves habitacions, reserves, clients...

Haurem de crear un programa per fer les gestions de Hooking, com a mínim ha de tenir dos funcionalitats bàsiques. Una d’elles ha de ser donar d’alta nous clients i una altra poder fer noves reserves.

Quan ens demanin afegir un client nou, haurem de demanar les respectives dades i afegir el client a la taula corresponent de la base de dades. Quan se’ns demani fer una nova reserva, haurem de recollir i mostrar les dades necessàries per tal que l’usuari pugui realitzar la reserva (seleccionar un client, un hotel, una de les habitacions de les que disposa l’hotel, data entrada, data sortida, calcular import…)

Per realitzar aquesta pràctica es demana que es programi mitjançant la utilització de varies classes. Tindrem una classe estàtica DB_Utils que contindrà les dades de connexió a la base de dades i les operacions relatives a ella.
Es demana també que implementem el patró DAO per accedir a les dades dels objectes. Per cada tipus de dada s’hauria de crear el seu patró DAO, i aquest serà qui farà les tasques necessaries per accedir a la informació corresponent.


# Base de Dades

La base de dades té l'estructura següent:



# Autor

- Juan José Gómez Villegas
- 
