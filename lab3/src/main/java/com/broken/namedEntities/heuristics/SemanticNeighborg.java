
package com.broken.namedEntities.heuristics;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;


public class SemanticNeighborg extends Heuristic {
    private static Set<String> keyWords = new HashSet<>(List.of(
        //Locaciones
        "Ciudad", "País", "Región", "Continente", "Estado", "Provincia", "Municipio", "Pueblo", "Villa", "Distrito", "Universidad",
        "Barrio", "Zona", "Territorio", "Isla", "Archipiélago", "República", "Federación", "Comunidad Autónoma", "Departamento", "Nación", "Territorio",
        "Cantón", "Parroquia", "Comuna", "Aldea","Gabinete", "Corte", "Cámara", "Asamblea", "Congreso", "Senado", "Parlamento", "Gobierno", "Ayuntamiento",
        "Municipio", "Concejo", "Comisión", "Comité", "Junta", "Consejo", "Tribunal", "Juzgado", "Fiscalía", "Ministerio", "Secretaría", "Dirección", "Gerencia",
        "Oficina", "Delegación", "Embajada", "Consulado", "Estadio", "Club", "Equipo", "Selección", "Federación", "Liga", "Copa", "Torneo", "Campeonato",
        "Partido", "Juego", "Competencia", "Evento", "Fiesta", "Celebración", "Festival", "Carnaval", "Desfile", "Concierto", "Recital", "Espectáculo", "Obra",
        "Película", "Serie", "Programa", "Documental", "Noticiero", "Revista", "Periódico", "Diario", "Portal", "Sitio", "Blog", "Red", "Foro", "Chat",
        /*Profesiones"Presidente*/ "Gobernador", "Técnico", "Licenciado", "Músico", "Vocero", "Ministro", "Doctor",
        "Ingeniero", "Profesor", "Abogado", "Juez", "Empresario", "Director", "Gerente", "Asesor", "Consultor", "Investigador", "Científico", 
        "Artista","Cabildo", "Iglesia","Escritor", "Periodista", "Actor", "Deportista", "Chef", "Arquitecto", "Contador", "Enfermero", "Paramédico", "Piloto", "Capitán",
        "General", "Almirante", "Sargento", "Soldado", "Policía", "Detective", "Guardia", "Vigilante", "Bombero", "Rescatista", "Paramédico", "Médico", "Enfermero", "Farmacéutico",
        "Psicólogo", "Psiquiatra", "Terapeuta", "Consejero", "Entrenador", "Nutricionista", "Dietista", "Fisioterapeuta", "Kinesiólogo", "Masajista", "Estilista", "Maquillador", "Diseñador",
        "Decorador", "Carpintero", "Albañil", "Electricista", "Plomero", "Mecánico", "Soldador", "Pintor", "Jardinero", "Agricultor", "Ganadero", "Pescador", "Cazador", "Carpintero", "Albañil",
        "Electricista", "Plomero", "Mecánico", "Soldador", "Pintor", "Jardinero", "Agricultor", "Ganadero", "Pescador", "Cazador", "Carpintero", "Albañil", "Electricista", "Plomero", "Mecánico",
        "Soldador", "Pintor", "Jardinero", "Agricultor", "Ganadero", "Pescador", "Cazador", "Carpintero", "Albañil", "Electricista", "Plomero", "Mecánico", "Soldador", "Pintor", "Jardinero", "Agricultor",
        "Ganadero", "Pescador", "Cazador", "Carpintero", "Albañil", "Electricista", "Plomero", "Mecánico", "Soldador", "Pintor", "Jardinero", "Agricultor", "Ganadero", "Pescador", "Cazador", "Carpintero", "Albañil",
        "Obispo","Arzobispo","Cardenal","Papa","Sacerdote","Pastor","Rabino","Imán","Monje","Monja","Fraile","Hermana","Hermano","Misionero","Evangelista","Cura","Vicario","Diácono","Diaconisa","Presbítero","Pastor","Pastora",
        "Rector","Vicario","Diácono","Diaconisa","Presbítero","Pastor","Pastora","Rector","Vicario","Diácono","Diaconisa","Presbítero","Pastor","Pastora","Rector","Vicario","Diácono","Diaconisa","Presbítero","Pastor","Pastora","Rector",
        "arzobispo" 
    ));  
   private static Set<String> Org = new HashSet<> (List.of(
        "Naciones Unidas", "Unión Europea", "OTAN", "OMS", "OIT", "FMI", "Banco Mundial", "OEA", "Unesco", 
        "Interpol", "Cruz Roja", "OMS", "Unicef", "ACNUR", "OPEP", "G7", "G20", "BRICS",
        "Google", "Apple", "Amazon", "Microsoft", "Facebook", "Tesla", "Samsung", "Sony", "IBM", "Intel", "NVIDIA", "Coca-Cola", "Pepsi", 
        "McDonald's", "Nike", "Adidas", "Toyota", "Ford", "General Motors", "Volkswagen", "BMW", "Mercedes-Benz", "Shell", "ExxonMobil", "Chevron", 
        "BP", "Walmart", "Costco", "Target", "Alibaba", "Tencent", "Baidu", "Huawei", "Netflix", "Spotify", "Uber", "Airbnb", "SpaceX", "Boeing", 
        "Lockheed Martin", "Goldman Sachs", "JPMorgan Chase", "Bank of America", "Citibank", "Wells Fargo", "HSBC", "Santander", "BBVA", "ING", "Deutsche Bank", 
        "Siemens", "Philips", "Panasonic", "LG", "Lenovo", "Acer", "Asus","Casa Rosada" , "Casa Blanca", "Palacio de Buckingham", "Palacio de la Moncloa", "Palacio de la Zarzuela", "Palacio de la Alvorada", "Palacio de Planalto", "Palacio de Hacienda", "Palacio de Justicia", "Palacio de Gobierno",
        "Casino","Iglesia"
        ,"Telefe","Canal 13","América TV","TV Pública","El Trece","Televisión Pública","Televisión Pública","Televisión Pública","Televisión Pública","Televisión Pública"
    ));
    private static Set<String> Locations = new HashSet<>(List.of(
        "Jujuy","Corrientes","Misiones","Formosa","Salta","Chaco","Tucumán","Catamarca","La Rioja","San Juan","Mendoza"
        ,"San Luis","Córdoba","Santa Fe","Entre Ríos","Buenos Aires","La Pampa","Neuquén","Junin","Rio Segundo","Rio Tercer"
        ,"Rio Cuarto","Rio Primero","Rio Quinto","Rio Cuarto","Rio Quinto","Rio Cuarto","Rio Quinto","Rio Cuarto","Rio Quinto"
        ,"America","Asia","Africa","Europa","Oceanía","Antártida","Argentina","Bolivia","Brasil","Chile","Colombia","Ecuador","Guyana","Paraguay","Perú","Surinam","Uruguay","Venezuela"
        ,"Estados Unidos","Canadá","México","Guatemala","Belice","Honduras","El Salvador","Nicaragua","Costa Rica","Panamá","Cuba","Jamaica","Haití","República Dominicana","Puerto Rico","Barbados","Trinidad y Tobago","Bahamas","Santa Lucía","San Cristóbal y Nieves","San Vicente y las Granadinas","Antigua y Barbuda","Granada","Dominica","San Martín","San Bartolomé","San Pedro y Miquelón","Aruba","Curazao","Bonaire","Saba","San Eustaquio","Islas Vírgenes de los Estados Unidos","Islas Vírgenes Británicas","Anguila","Montserrat","Guadalupe","Martinica","San Pedro y Miquelón","San Bartolomé","San Martín","San Cristóbal y Nieves","Santa Lucía","San Vicente y las Granadinas","Granada","Barbados","Trinidad y Tobago","Bahamas","Antigua y Barbuda","Dominica","San Martín","San Bartolomé","San Pedro y Miquelón","Aruba","Curazao","Bonaire","Saba","San Eustaquio","Islas Vírgenes de los Estados Unidos","Islas Vírgenes Británicas","Anguila","Montserrat","Guadalupe","Martinica","San Pedro y Miquelón","San Bartolomé","San Martín","San Cristóbal y Nieves","Santa Lucía","San Vicente y las Granadinas","Granada","Barbados","Trinidad y Tobago","Bahamas","Antigua y Barbuda","Dominica","San Martín","San Bartolomé","San Pedro y Miquelón","Aruba","Curazao","Bonaire","Saba","San Eustaquio","Islas Vírgenes de los Estados Unidos","Islas Vírgenes Británicas","Anguila","Montserrat","Guadalupe","Martinica","San Pedro y Miquelón","San Bartolomé","San Martín","San Cristóbal y Nieves","Santa Lucía","San Vicente y las Granadinas","Granada","Barbados"
        ,"América" , "Europa"
    ));
    private static Set <String> Names =new HashSet<>(List.of(
        "Joel","Maximo","Patricia","Javier","Mauricio","Alberto","Cristina",
        "Miguel","Juan","Pedro","Carlos","Maria","Jose","Luis","Ana","Laura","Sofia","Lucia","Valentina","Isabella","Emma","Mia","Camila","Victoria","Zoe","Emilia","Renata","Martina","Sara","Julia","Mariana","Ximena","Valeria","Natalia","Gabriela","Daniela","Alejandra","Fernanda","Paola","Andrea","Monica","Diana","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia",
        "Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica"
        ,"Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana","Rafaela","Josefina","Teresa","Dora","Rosa","Elena","Martha","Gloria","Liliana","Patricia","Claudia","Veronica","Silvia","Adriana","Carmen","Rocio","Esther","Lorena","Ruth","Alicia","Dolores","Beatriz","Eva","Catalina","Antonia","Juana"
        ));
    private static List<String> excludedWords = List.of(
        "de", "del", "El", "el", "La", "la", "Los", "los", "Las", "las","le","Le","habló","dijo"
        ,"dijieron","dijeron","desde","Desde","logró","logro","lograron","logro","lograron","lograron","lograron","lograron","lograron"

    );
    public SemanticNeighborg(String heuristicName) {
        super(heuristicName);
    }

    @Override
    public List<String> extractCandidates(String text) {
        List<String> candidates = new ArrayList<>();
        String[] words = text.replaceAll("[\\[\\]<>//]", "").split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if ((!Org.contains(words[i])) && (!Locations.contains(words[i])) ) {
                if (keyWords.contains(words[i] )|| (Names.contains(words[i]))) {
                    if (i + 1 < words.length && !excludedWords.contains(words[i+1])) {
                        candidates.add(words[i + 1]);   
                    } else if (i + 2 < words.length && !excludedWords.contains(words[i+2])) {
                        candidates.add(words[i + 2]);
                    }
                }
            } else {
                candidates.add(words[i]);
            }
        }
        return candidates;
    }
}
