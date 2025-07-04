package com.example.pelisplay

// Repositorio con los datos de cada película
object PeliculaRepository {
    fun obtenerDetallePorTitulo(titulo: String): DetallePelicula {
        return when (titulo) {
            "Avengers" -> DetallePelicula("Un grupo de superhéroes se une para salvar al mundo de una amenaza global.", "Acción", "2h 23m", 2012, "https://www.youtube.com/watch?v=6ZfuNTqbHE8")
            "El Conjuro" -> DetallePelicula("Investigadores paranormales enfrentan fuerzas demoníacas.", "Terror", "1h 52m", 2013, "https://www.youtube.com/watch?v=k10ETZ41q5o")
            "Black Panther" -> DetallePelicula("El príncipe T'Challa regresa a Wakanda para ser rey, pero enemigos amenazan la paz.", "Acción", "2h 14m", 2018, "https://www.youtube.com/watch?v=xjDjIWPwcPU")
            "Rápidos y Furiosos" -> DetallePelicula("Carreras ilegales y acción explosiva entre familia y enemigos.", "Acción", "2h 10m", 2001, "https://www.youtube.com/watch?v=uisBaTkQAEs")
            "Rincon del Mundo" -> DetallePelicula("Una mujer japonesa lucha por mantener la esperanza durante la Segunda Guerra Mundial.", "Drama", "2h 8m", 2016, "https://www.youtube.com/watch?v=XR59vQK0s7k")
            "Purple Hearts" -> DetallePelicula("Una cantante y un marine se casan por conveniencia, pero nace el amor verdadero.", "Romántico", "2h 2m", 2022, "https://www.youtube.com/watch?v=WTLgg8oRSBE")
            "Inception" -> DetallePelicula("Un ladrón entra en los sueños para robar secretos, enfrentando su propia mente.", "Ciencia Ficción", "2h 28m", 2010, "https://www.youtube.com/watch?v=YoHD9XEInc0")
            "Palmer" -> DetallePelicula("Un exconvicto forma un vínculo inesperado con un niño rechazado por su familia.", "Drama", "1h 50m", 2021, "https://www.youtube.com/watch?v=0U7wEzKp5KI")
            "Me Before You" -> DetallePelicula("Una joven cuida a un hombre paralizado y descubren el amor.", "Romántico", "1h 50m", 2016, "https://www.youtube.com/watch?v=Eh993__rOxA")
            "Encanto" -> DetallePelicula("Una familia mágica colombiana enfrenta la pérdida de sus poderes.", "Animación", "1h 42m", 2021, "https://www.youtube.com/watch?v=CaimKeDcudo")
            "La Monja" -> DetallePelicula("Un sacerdote investiga la misteriosa muerte de una monja.", "Terror", "1h 36m", 2018, "https://www.youtube.com/watch?v=dnQ0Q6UxA9g")
            "Frozen" -> DetallePelicula("Una princesa parte en busca de su hermana con poderes de hielo.", "Animación", "1h 42m", 2013, "https://www.youtube.com/watch?v=TbQm5doF_Uc")
            "Toy Story" -> DetallePelicula("Los juguetes cobran vida cuando los humanos no están.", "Animación", "1h 21m", 1995, "https://www.youtube.com/watch?v=KYz2wyBy3kc")
            "Up" -> DetallePelicula("Un anciano vuela su casa con globos y emprende una aventura.", "Animación", "1h 36m", 2009, "https://www.youtube.com/watch?v=pkqzFUhGPJg")
            "The Fault in Our Stars" -> DetallePelicula("Dos adolescentes con cáncer viven una historia de amor.", "Romántico", "2h 6m", 2014, "https://www.youtube.com/watch?v=9ItBvH5J6ss")
            "El Grand Magasin" -> DetallePelicula("Un recorrido caótico y cómico dentro de una gran tienda por departamentos.", "Comedia", "1h 27m", 1973, "https://www.youtube.com/watch?v=H0kB2Yj6KEw")
            "Tu Color" -> DetallePelicula("Dos amigos buscan escapar de una ciudad opresiva, enfrentando realidades dolorosas.", "Drama", "1h 36m", 2020, "https://www.youtube.com/watch?v=XHlAT6NN7T0")
            "Jocker" -> DetallePelicula("Un comediante fallido desciende a la locura y el crimen.", "Drama", "2h 2m", 2019, "https://www.youtube.com/watch?v=zAGVQLHvwOY")
            "It" -> DetallePelicula("Un grupo de niños enfrenta a una entidad maligna que toma forma de payaso.", "Terror", "2h 15m", 2017, "https://www.youtube.com/watch?v=xhJ5P7Up3jA")
            "Misión Imposible" -> DetallePelicula("Un agente secreto debe detener amenazas globales usando habilidades extremas.", "Acción", "2h 10m", 1996, "https://www.youtube.com/watch?v=AvYq3UiqfW8")
            "Norbit" -> DetallePelicula("Un hombre tímido es atrapado entre su amor verdadero y una novia dominante.", "Comedia", "1h 42m", 2007, "https://www.youtube.com/watch?v=gaQX6t1Dysg")
            "El Hobbit" -> DetallePelicula("Bilbo Bolsón emprende una aventura con enanos para recuperar su tierra.", "Aventura", "2h 49m", 2012, "https://www.youtube.com/watch?v=JTSoD4BBCJc")
            "Red, White & Royal Blue" -> DetallePelicula("El hijo de la presidenta de EE.UU. se enamora del príncipe británico.", "Romántico", "2h 1m", 2023, "https://www.youtube.com/watch?v=pt56IC8gDZ4")
            "La Idea de Ti" -> DetallePelicula("Una madre soltera se enamora de un cantante 20 años menor.","Romántico","1h 55m", 2024,"https://www.youtube.com/watch?v=t-fcs1ydbVA")
            "Culpa Mia" -> DetallePelicula("Una joven se enamora del hijo del nuevo esposo de su madre, desatando un amor prohibido.", "Romántico", "1h 57m", 2023, "https://www.youtube.com/watch?v=ZVnxgZor-3o")
            "Culpa Tuya" -> DetallePelicula("Los secretos y desafíos amenazan la relación entre Noah y Nick.", "Romántico", "1h 55m", 2024, "https://www.youtube.com/watch?v=sK7kT3Va2xY")
            "Culpa Nuestra" -> DetallePelicula("Noah y Nick enfrentan su destino en el cierre de su historia de amor.", "Romántico", "1h 58m", 2025, "https://www.youtube.com/watch?v=KZ-cwTPeDOA")

            "A través de mi ventana" -> DetallePelicula("Raquel está enamorada de su vecino y hará todo para que se fije en ella.", "Romántico", "1h 53m", 2022, "https://www.youtube.com/watch?v=tpVcG8LRI2k")
            "A través del mar" -> DetallePelicula("Raquel y Ares se reencuentran, pero los problemas no han terminado.", "Romántico", "1h 48m", 2023, "https://www.youtube.com/watch?v=wNVHJoxbV9Y")
            "A través de tu mirada" -> DetallePelicula("La historia continúa con nuevos desafíos en la relación entre Raquel y Ares.", "Romántico", "1h 50m", 2024, "https://www.youtube.com/watch?v=4ZPZlmcDAs0")
            "Con Todos menos Contigo" -> DetallePelicula("Dos excompañeros de universidad fingen ser pareja en una boda, pero la química es real.", "Romántico", "1h 43m", 2023, "https://www.youtube.com/watch?v=9v2Goq9TLjI")
            else -> DetallePelicula("Descripción no disponible.", "Género desconocido", "Duración desconocida", 0, "https://www.youtube.com")
        }
    }
}
