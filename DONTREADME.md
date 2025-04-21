# Main
*vet/src/main/java/app/Main.java*

# Flujo
1. Crea la carpeta de app.domains.models y crea los diferentes usuarios.
Empezar por el principal pa que luego los otros que heredan esten completos.
Construir contructores con todos los args y no args

2. Empezó a crear los servicios

3. Para los servicios necesitaba los puertos

4. Luego las entidades en los adaptadores.

5. Archivo de adaptadores padre, se crea el objeto y se recopilan datos que se guardarán en la bd

6. Siguió los repositorios en los adaptadores 
(es donde estan los queries para interactuar con la bd)

# ------------------ Mas notas

Toda la app es manejada por el Dominio
El dominio tiene: Modelos y servicios
⦁	Modelos: Todas las entidades presentes en mi negocio
⦁	Servicios: Son las reglas de negocio
* Todos los puertos se deben conectar con un adaptador, es decir,
van con una clase que implementa ese puerto

Los servicios NO necesitan interfaces
La historia clínica es un servicio
Todos los adaptadores llevan la etiqueta @service, excepto por los inputs que llevan @component
Todo lo que tenga que ver con la bd a los adaptadores :) 
Los puertos pueden llevar save, el que no lo necesita es el repository

Los datos ingresan por input, del input al services, y del services al adapter

Para editar registro save pero con la primarykey seteada

# -------------------- Preguntas
una historia clinica solo puede tener una orden
y una orden solo puede tener una historia clínica
la relacion es 1 a 1? 
pero y si yo quiero acceder a la historia desde una orden y viceversa? 
no se puede, a la hora de guardar la referencia una de las dos instancias va a estar creada y la otra quedará sin referencia