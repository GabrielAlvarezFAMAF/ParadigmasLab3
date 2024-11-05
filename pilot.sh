#!/usr/bin/env bash

#Mensaje de help para no perder tiempo 
if [[ "$@" = *--help ]] || [[ "$@" = *-h ]]; then
    echo "Usage: ./pilot.sh [OPTION]"
    echo "OPTIONS:"
    echo "-h, --help:                           Show this help message and exit"
    echo "-b,                                   a file in resources"
    echo "-ne, --named-entity <heuristicName>:  Use the specified heuristic to extract named entities
                                                Available heuristic names are:
                                                Smnbh : captures words that are not in the stop words list and have a semantic neighbor
                                                CapitalizedWordHeuristic: captures capitalized words 
                                                FiltredCwh: captures capitalized words that are not in the stop words list"
                                                
    echo "-sf, --stats-format <format>:         Print the stats in the specified format
                                                Available formats are: 
                                                cat: Category-wise stats
                                                topic: Topic-wise stats"
    
    exit 1
fi

#Usamos el archivo spark-3.5.1-bin-hadoop3
echo "En caso de no tener spark 3.5.1, se descargará"
sleep 5
if [ -d "spark-3.5.1-bin-hadoop3" ]; then
    echo "Spark 3.5.1 ya está descargado"
else
    wget https://archive.apache.org/dist/spark/spark-3.5.1/spark-3.5.1-bin-hadoop3.tgz
    tar -xvzf spark-3.5.1-bin-hadoop3.tgz
    rm spark-3.5.1-bin-hadoop3.tgz
fi

# Configuraciones para dos workers

cd spark-3.5.1-bin-hadoop3/conf
touch spark-env.sh 
echo "SPARK_WORKER_INSTANCES=2" > spark-env.sh
echo "SPARK_WORKER_CORES=1" >> spark-env.sh
echo "SPARK_WORKER_MEMORY=1g" >> spark-env.sh

# Confeccionamos el cluster 

cd ../sbin
./start-master.sh
./start-worker.sh spark://$(hostname -f):7077

# Compilar el codigo con maven

cd ../../
if command -v mvn &> /dev/null; then
    echo "Maven está instalado."
else
    echo "Maven no está instalado, vaya buscando su password"
    sudo apt install maven
    sleep 5
fi

cd lab3/
mvn install 

#Ahora si, ejecutemos el codigo
echo "Seria un buen momento para abrir el navegador en la direccion: http://localhost:8080"
sleep 5
cd ../spark-3.5.1-bin-hadoop3

bin/spark-submit --master spark://$(hostname -f):7077 ../lab3/target/App-1.0.0.jar "$@" --driver-class-path ../lab3/lib/json-20240303.jar 2>/dev/null

# Detenemos el cluster
echo "Para detener el cluster ejecute los siguientes comandos"
echo "en el path spark-3.5.1-bin-hadoop3/sbin" 
echo "para detener los workers ./stop-worker.sh"
echo "para detener el master ./stop-master.sh"


