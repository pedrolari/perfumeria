# perfumeria

Iniciamos el proyecto de JAVA de Gestion de una Perfumeria

<<<<<<< HEAD
<<<<<<< HEAD
Alwex

PROYECTO SISTEMAS DE GESTION EMPRESARIAL (Para el 21/12)

Aplicación que gestione una perfumería:
Mínimos contenidos:
	Comprar a proveedores (por parte del empresario)
	Venta a clientes (por parte del empresaro)
	Articulos, lo que se compra y vende.
	Cada vez que se vende se genera un ticket
	Y si necesita factura el cliente, también
	Varios usuarios en función de los usuarios de la aplicación. El jefe puede dar de alta hasta empleados. (Tambien clientes).
	Realiza pedidos al proveedor. No es lo mismo hacer el pedido que recepcionarlo
	Si el empresario realiza un pedido determinado a un cualquier proveedor,  a lo mejor necesito generar informes en pdf, ticket, factura, y otras opciones que sean menesteres
Pdf, crystal report

Todos los jueves se enseña a eva
Funcione, atractiva a la vista, 

Bases de datos
Empleado: user varchar(20),pass varchar(20),nombre varchar(20),apellidos varchar(20),telefono int,rol varchar(20)
Falta comprobar resto tablas
Proveedor: nif/cif, nombre, tlf,direccion, 
Cliente: dni,nombre,apellido,direccion,tlf,sexo,fecha_naci,fecha_ingreso
Articulos: id_art,nombre,descrip,volumen,embalaje,nif/cif,id_cat
Compras:id_compra,user,cif/nif,fecha_compra,total(opcional)
Lineas de compras:id_lineac, id_compra, cant, precio
Ventas: id_venta, user,dni(opcional, puede ser vacio),fecha_venta,
Linea de venta: id_lineav, id_venta,cant,precio
Categorías:
LOCALIDAD:
PROVINCIA: 

DIVISION TRABAJO:

Rellenar base de datos con 2 tuplas (Usuario, proveedor,cliente). Rellenar articulos con bastantes (Rafa y Amadeo)

Clase Conexion 
Clase Usuario: (Beni)
Clase Proveedor: (Mario)
Clase Cliente: (Amadeo)
Clase Articulos: (Luis)
Interfaz: (Emilio)
Clase validacion: (Carlos)
Clase fecha: 
Funcionalidad impresion(mirar)


=======
=======
>>>>>>> 4839405a5fc2f62eab6cb32f5b475494b5b67482

Clase Conexion
Metodo para realizar la conexion a la BBDD
user: root
pass: 

Metodos:
	-consultar(String)
	-modificar(String)
<<<<<<< HEAD
>>>>>>> 4839405a5fc2f62eab6cb32f5b475494b5b67482
=======
>>>>>>> 4839405a5fc2f62eab6cb32f5b475494b5b67482
