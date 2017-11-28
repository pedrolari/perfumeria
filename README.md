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

articulos(id_articulo int, nombre varchar(20), precio decimal, descripcion varchar(255), volumen varchar(20), embalaje varchar(20), cif varchar(9), id_categoria int, stock int)
categorias(id_categoria int, nombre varchar(20), id_categoria_padre int)
clientes(dni varchar(9), nombre varchar(20), apellidos varchar(20), direccion varchar(20), telefono int, email varchar(20), sexo char, fecha_nacimiento date, fecha_ingreso date)
compras(id_compra int, user varchar(20), cif varchar(9), fecha_compra date)
empleados(user varchar(20), pass varchar(20), nombre varchar(20), apellidos varchar(20), telefono int, rol int)
lineas de compras(id_lineas_de_compra int, id_compra int, cantidad int, precio decimal)
lineas de venta(id_linea_de_venta int, id_venta int, cantidad int, precio decimal)
localidades(id_localidad int, nombre varchar(20), id_provincia int)
proveedores(cif varchar(9), nombre varchar(255), telefono int, direccion varchar(20))
provincias(id_provincia int, nombre varchar(255))
ventas(id_venta int, user varchar(20), dni varchar(9), fecha_venta date) 

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

Funciones Administrador: 
	- Empleados(Alta, modificacion, Baja)
	- proveedores(alta,modificaciones, baja, pedido)
	- cliente (Alta, modificacion, baja, compra)
	- articulo (alta,modificacion,baja,compra,pedido)

Funciones empleado:
	- cliente (Alta, modificacion, baja, compra), 
	- articulo (alta,modificacion,baja,compra,pedido)
	- proveedores(alta,modificaciones, baja, pedido)


Primera funcionalidad:
	Cliente: 
		- Alta (Emilio)
	Empleado:
		- Alta (Manuel)
		- Modificacion (Luis)
		- Baja (Amadeo)
	Proveedor
		- Alta (Carlos)
		-Modificacion (Manuel)
		-Pedido(Rafa)
	Articulo
		-Alta(Amadeo)
		-Modificacion(Alejandro)
