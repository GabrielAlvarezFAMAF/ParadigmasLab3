package com.broken.namedEntities.heuristics;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


public class FiltredCwh extends CapitalizedWordHeuristic {
    /*implements Heuristic  also but its redundant*/
    private static Set<String> articulos = new HashSet <>(List.of(
        "el", "la", "los", "las", "un", "una", "unos", "unas", "El", "La", "Los", "Las", "Un", "Una", "Unos", "Unas"));
    
    private static Set<String> verbos = new HashSet <>(List.of(
        "ando", "iendo", "ido", "amos", "emos", "imos", "ais", "eis", 
        "aba", "abas", "ias", "abamos", "iamos", "abais", "iais", "aban", "ian","aste", "iste",  
        "amos", "imos", "asteis", "isteis", "aron", "ieron", "are", "ere", "ire", "aras", "eras", "iras", "ara", "era", "ira", 
        "aremos", "eremos", "iremos", "areis", "ereis", "ireis", "aran", "eran", "iran", "aria", "eria", "iria", "arias", "erias", "irias", 
        "ariamos", "eriamos", "iriamos", "ariais", "eriais", "iriais", "arian", "erian", "irian", "ando", "iendo","ido", "cho",
        "íamos","ó","í","ió","ieron","ído","ídos","ídas "
        
    ));
    

    private static Set<String> preposiciones = new HashSet <>(List.of("a", "ante", "bajo", "cabe", "con", "contra", "de", "desde", 
    "durante", "en", "entre", "hacia", "hasta", "mediante", "para", "por", "según", "sin", "so", "sobre", "tras", "A", "Ante", "Bajo", "Cabe", 
    "Con", "Contra", "De", "Desde", "Durante", "En", "Entre", "Hacia", "Hasta", "Mediante", "Para", "Por", "Según", "Sin", "So", "Sobre", "Tras",
    "A", "Ante", "Bajo", "Cabe", "Con", "Contra", "De", "Desde", "Durante", "En", "Entre", "Hacia", "Hasta", "Mediante", "Para", "Por", "Según", "Sin", "So", "Sobre", "Tras"

    ));

    private static Set<String> conectores = new HashSet <>(List.of("Y", "O", "Pero", "Sino", "Aunque", "Porque", "Pues", "Mientras", "Cuando", "Si", "Que", "Como", 
    "Donde", "Ya", "A", "Así", "A", "A", "Antes", "Con", "Después", "En", "Hasta", "Para", "Siempre", "Tan", "Además", "Sin", "Por", "Es", "Por", "O", "Por", 
    "Entonces", "Por"," El"," "
    ));


    public FiltredCwh(String heursticName) {
        super(heursticName);
    }
    @Override
    public List<String> extractCandidates(String text) {
        List<String> candidates = super.extractCandidates(text);
        Iterator < String> it= candidates.iterator();
        boolean flag = false;
        while (it.hasNext()){
            String candidate = it.next();
            for(String sufijo : verbos){
                if(candidate.endsWith(sufijo)){
                    it.remove();
                    flag = true; 
                    break;
                }
            }
            if(!flag && (articulos.contains(candidate) || preposiciones.contains(candidate) || conectores.contains(candidate))){
                it.remove();
            }
        flag = false;
        }
        return candidates;
    }
}
