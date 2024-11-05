# Computacion distribuida con Apache Spark

## Tabla de Contenidos
1. [Resumen](#resumen)
2. [Introduccion](#introduccion)
3. [Desarrollo](#parte-1)
4. [Practica](#practica)
5. [Discusiones](#discusiones)

## Resumen
El presente trabajo se centro en la implementacion de un cluster spark master-dos workers sobre el programa de entidades nombradas del laboratorio pasado. Se aprovecho la oportunidad de construir un scrip para la ejecucion de este trabajo.

## Introduccion
Contando con la idea del trabajo pasado se planteo una situacion hipotetica donde la aplicacion diseñada tendria que computar entidades nombradas sobre un archivo demasiado extenso, es decir, big data. Surgio entonces la problematica en cuanto al desempeño del codigo y lograr que este responda en una cantidad de tiempo razonable. Este escenario parecio encontrar respuesta en la computacion distribuida, la cual consta de repartir el computo de nuestra aplicacion en distintos dispositivos. El objetivo fue comprender como funciona Apache Spark para distribuir servicios e implementarlo en el codigo del laboratorio pasado con el fin de optimizar el computo de entidades nombradas con una heuristica determinada.

### Metodologia de trabajo 
Se utilizo la tecnologia Apache Spark, un framework de computación en clúster de codigo abierto, el cual funciona mediante un master y diversos workers en los cuales distribuye porciones de trabajo y junta los resultados parciales para dar una solucion completa al finalizarse. La idea del framwork es poder alivianar el peso entre dispositivos, sin embargo no contamos con eso, asi que todas las instancias de spark fueron en una misma computadora, es decir en local. Utilizamos su api para Java y poder llamarlo directamente desde el codigo, permitiendo una interaccion dada por el hilo de ejecucion del codigo.

Otra de las tecnologias que utilizaremos sera 'Maven', una herramienta de software para la gestión y construcción de proyectos Java, un compilador mas especifico y estructurado que ayuda a organizar las librerias necesarias para compatibilizar nuestro codigo con el cluster de Spark.

A continuacion las librerias necesarias para manejar Spark dentro del codigo:
```Java 
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
```
## Desarrollo 
EL codigo se divide en dos carpetas que representan ambas tecnologias y ademas instancias distintas en la ejecucion del proyecto. 
En un primer lugar hablaremos de `lab3` donde se encuentra la carpeta `lib` perteneciente al laboratorio pasado, esto debido a que sin el .jar que contiene, tendremos conflicto con los archivos .json

Luego `src/main` donde podremos encontrar tanto a `resources` y `java/com/broken`.
En el segundo directorio yace todo el codigo del programa, mientras que en resources encontraremos el 'directory data', gracias a maven podemos manejar distintos tipos de archivos.
```xml
<plugin>
    <artifactId>maven-resources-plugin</artifactId>
    <version>3.0.2</version>
    <configuration>
        <resources>
            <resource>
                <directory>src/main/resources/data</directory>
                <includes>
                    <include>dictionary.json</include>
                    <include>feeds.json</include>
                    <include>quijote.txt</include>
                </includes>
            </resource>
        </resources>
    </configuration>
</plugin>
```

Los unicos cambios significativos sucedieron principalmente en App.java con respecto a Spark, además se agrego una abstraccion extra de las 'categorias' de 'NameEntities' para que responda apropiadamente al POO.

### Crear un big data
Para modificar lo menos posible el codigo previo utilizamos las funcionalidades ya hechas, donde dirigimos los feeds de noticias directamente a un archivo llamado *Feeds.txt* con la intencion de que su lectura de dicho .txt sea distribuida entre los workers, aliviando el computo.

```Java
JavaRDD<String> text = spark.read().textFile(name).javaRDD();
String lines = text.reduce((s1, s2) -> s1 + " " + s2);
```
### SparkSession
Posteriormente a esto se creo una *sesion de Spark*
```Java
SparkSession spark = SparkSession.builder()
            .appName("App")
            .getOrCreate();
```
tambien se pueden instanciar otras configuraciones, como por ejemplo 

```Java
.master("spark://virtualgohan:7077")
```
en dicho caso no seria necesario instanciar una ruta para el cluster a la hora de ejecutar todo por linea de comando. 

Y para finalizar la seccion distribida usamos 
```Java
spark.stop();
```
Todas estas modificaciones son propias del manejo de la api de spark para los codigos de Java. 

Otros cambios fueron los path de los .json como mencionamos anteriorment, se agregaron mas diarios al archivo `feeds.json`, ademas de cambiar el path para acceder a el y a `dictionary.json`

```Java
feedsDataArray = JSONParser.parseJsonFeedsData("../lab3/src/main/resources/data/feeds.json");
...
dataDict = JSONParser.parseJsonDictionaryData("../lab3/src/main/resources/data/dictionary.json");

```

Esto se debe a que el comando de ejecucion es procesado en la carpeta de spark, donde se pasa como argumento un .jar construido con maven, por tanto el path debe hacerse en relacion a ese directorio. 

Se considero cambiar el user interface para que el codigo pudiese soportar bigdata "externa" (se agregaron manualmente). 

## Practica 
### Como compilar y ejecutar

> En primer lugar, se confecciono un scrip `pilot.sh` el cual requiere el siguiente tratamiento  

- Verificar tener una version reciente de Java y las variables de entorno correspondientes correctmanete seteadas
- Pese a que el scrip contiene un hashbang, lo cual le permite ejecutarse directamente, puede que no funcione, en ese caso debera darle permisos de ejecucion con el siguiente comando.
    ```bash
    chmod +x archivo.sh
    ```
- Ejecute --help para saber que argumentos pasarle
    ```bash
    ./pilot.sh --help
    ```
- Elija los argumentos y ejecute el programa. A modo de ejemplo el siguiente codigo 
    ```bash
    ./pilot.sh -ne FiltredCwh -sf
    ```
> A continuacion un instructivo de ejecucion manual 
1. Compilacion de App
Ingresa a `lab3` el directorio donde esta el source del laboratorio anterior preparado en un entorno de maven. Abrir la consola y ejecutar

    ```bash
    $ mvn install
    ```
2. Montar un cluster
Posteriormente salir e ingresar a la carpeta de spark llamada `spark-3.5.1-bin-hadoop3` y seguir estas instrucciones:

    ```bash
    $ cd sbin/
    $ ./start-master.sh 
    ```
Si en este momento buscamos la url http://localhost:8080 encontraremos el cluster, buscamos la linea que dice **URL** y copiar la linea siguiente ($URL_SPARK_CLUSTER)Nuevamente en la consola 

    ```bash 
    $ ./start-worker.sh $URL_SPARK_CLUSTER  
    ```
Si volvemos al cluster notaremos que se han creado correctamente dos workers

3. Ejecutacion distribuida 
En este ultimo paso saldremos de la carpeta sbin/ y posicionados en la carpeta de spark ejecutaremos el programa en spark con esta linea de comando 
    ```bash
    $ bin/spark-submit --master $URL_SPARK_CLUSTER ../lab3/target/App-1.0.0.jar -ne FiltredCwh -sf --driver-class-path ../lab3/lib/json-20240303.jar 2>/dev/null

    ```

## Discusiones 

La confeccion de un scrip permitio la aplicacion de un paradigmas visto en el teorico, lo cual se considera una oportunidad aprovechada. Ademas, hace mas escalable el codigo como para leer archivos externos, siempre que se encuentren en la carpeta resources. 

El aprendizaje de nuevas tecnologias como frameworks fue un gran desafio. Al final se termino conociendo mucho sobre el lenguaje y las tecnologias, lo que a su vez llevo a la implementacion de un paradigma extra y un acercamiento a la intuicion de la experiencia de usuario, verificando la portabilidad del codigo.


