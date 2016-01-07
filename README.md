# Connection BD Java
Ejemplo aplicando Conexion a la BD PostgreSQL

##Ejemplo
Para poder conectarnos a nuestra BD en postgres, debemos tener en cuenta los parametros de conexion asi como tambien descargar el [.jar](http://mvnrepository.com/artifact/org.postgresql/postgresql/9.4.1207.jre7)
que nos permitira trabajar con el JDBC de postgres, cabe recalcar que dependiendo de la BD que manejemos los parametros son diferentes, en este caso en Postgres son los siguientes:
```java
public class ParametrosConexion {
    final String JDBC_DRIVER = "org.postgresql.Driver";
    final String DB_URL = "jdbc:postgresql://localhost:5432/DemoDB";
    final String USER = "postgres";
    final String PASS = "ixpo";
}
```

###Insert
Una vez creado nuestro parametros de conexion en nuestra clase donde utilizaremos esa BD crearemos un objeto de nuestra clase para poder 
acceder a cada una de ellas:
```java
ParametrosConexion parametros = new ParametrosConexion();
```
Ahora si creamos nuestro Objeto de conexion de la clase **Connection** de la sig. manera:
```java
Connection conexion = DriverManager.getConnection(parametros.DB_URL,parametros.USER,parametros.PASS)
```
Asignamos tambien el Driver a utilizar en la clase:
```java
Class.forName(parametros.JDBC_DRIVER);
```
Hacemos nuestra **Query** y lo guardamos en un Objeto de la clase **PreparedStatement**:
```java
PreparedStatement st = conexion.prepareStatement("INSERT INTO persona(nombre) VALUES('Ivan Gamaliel')");
```
Ejecutamos nuestra **Query** y cerramos la conexion, esto con el fin de no dejar abierto el espacio de memoria asignado al proceso:
```java
st.executeUpdate();
st.close();
```

###Select
Para poder recorrer un **Select** primero debemos crear nuestra **Query**
```java
PreparedStatement st = conexion.prepareStatement("SELECT * FROM persona");
```
Una vez que nuestro objeto tiene la Query lo siguiente sera guardar el resultado en Objeto de la clase **ResultSet**:
```java
ResultSet rs = st.executeQuery();
```
Recorremos nuestro resultado con el metodo del Objeto **.next**
```java
while (rs.next()){
    Persona per = new Persona(rs.getString("nombre"),rs.getInt("id"));
    listaPersona.add(per);
}
```
En este caso **nombre** y **id** son el nombre de los campos que vamos a extraer de nuestro resultado, al final cerramos nuestro
**ResultSet** y **Conexion**




