Feature: Flujo  Delivery Android

	Background:
		#@PRECOND_LB-1012
		Given Abrir Aplicacion en BrowserStack

	
	Scenario Outline: flujo de pedido delivery con usuario registrado en "<Pais>"
		And Seleccionar "<Pais>"
		    And Presionar boton Next luego de elegir "<Pais>"
		    When Dentro del mercado de "<Pais>" acceder a pedidos y hacer login con "<User>" y "<Pass>" registrada
		    Then Iniciar orden tipo delivery indicar "<Direccion>" seleccionar "<Categoria>" y "<Producto>"
		    And Seleccionar bebida "<Bebida>"
		    And Quitar "<Ingrediente>" y confirmar orden
		    And Editar "<IngredeinteExtra>" y guardar pedido
		    When Realizar pago, anadir codigo promocional "<Codigo>", seleccionar tipo de pago "<TipoPago>" y presionar boton  pagar
		    Then Dentro de pasarela de delivery pago ingresar datos de tarjeta de credito con numero de tarjeta "<NumeroTarjeta>" y fecha de vencimiento "<FechaVencimiento>" y codigo de seguridad "<CodigoSeguridad>"
		    @regresion
		    Examples:
		      | Pais        | User                       | Pass         | Direccion | Categoria | Producto | Bebida | Ingrediente | IngredeinteExtra | Codigo | TipoPago | NumeroTarjeta | FechaVencimiento | CodigoSeguridad |
		      | Argentina | pruebaautoqa@gmail.com    | Pruebas_2022 | Av. del Libertador 2800, Olivos           | McCombos            | Big Mac McCombo grande | Coca Cola           | Salsa Big Mac | Queso Cheddar        | TSOFT  | Mercado Pago | 5416752602582580 | 11/25            | 123             |
		      | Chile     | pruebaautoqachi@gmail.com | Pruebas_2022 | Avenida Camilo Henríquez 3100, La Florida | McCombos            | McCombo Grande BigMac  | Coca-Cola Original  | Salsa Big Mac | Queso Cheddar        | TSOFT  | card         | 5416752602582580 | 11/25            | 123             |
		      | Colombia    | pruebaautoqacol@gmail.com  | Pruebas_2022 |           |           |          |        |             |                  |        |          |               |                  |                 |
		      | Costa Rica  | pruebaautoqacos@gmail.com  | Pruebas_2022 |           |           |          |        |             |                  |        |          |               |                  |                 |
		      | México      | pruebaautoqamex@gmail.com  | Pruebas_2022 |           |           |          |        |             |                  |        |          |               |                  |                 |
		      | Panamá      | pruebaautoqapan@gmail.com  | Pruebas_2022 |           |           |          |        |             |                  |        |          |               |                  |                 |
		      | Puerto Rico | pruebaautoqapuer@gmail.com | Pruebas_2022 |           |           |          |        |             |                  |        |          |               |                  |                 |
		      | Uruguay     | pruebaautoqauru@gmail.com  | Pruebas_2022 |           |           |          |        |             |                  |        |          |               |                  |                 |
		    @smokeTest
		    Examples:
		      | Pais      | User                      | Pass         | Direccion                                 | Categoria           | Producto               | Bebida              | Ingrediente   | IngredeinteExtra     | Codigo | TipoPago     | NumeroTarjeta    | FechaVencimiento | CodigoSeguridad |
		     # | Brasil    | pruebaautoqabra@gmail.com | Pruebas_2022 | Rua Funchal, 500, SAO PAULO                | McOfertas Clássicas | McOferta Média Big Mac | Fanta Guaraná 400ml | Sal | Fatia Queijo Cheddar | TSOFT  | creditCard   | 5555341244441115 | 03/30            | 737             |
		     # | Argentina | pruebaautoqa@gmail.com    | Pruebas_2022 | Av. del Libertador 2800, Olivos           | McCombos            | Big Mac McCombo grande | Coca Cola           | Salsa Big Mac | Queso Cheddar        | TSOFT  | Mercado Pago | 5416752602582580 | 11/25            | 123             |
		      | Chile     | pruebaautoqachi@gmail.com | Pruebas_2022 | Paseo Ahumada 100, Santiago | McCombos            | McCombo Grande BigMac  | Coca-Cola Original  | Salsa Big Mac | Queso Cheddar        | TSOFT  | card         | 5416752602582580 | 11/25            | 123             |
		      #| Colombia     | pruebaautoqacol@gmail.com | Pruebas_2022 | Calle 125, Bogotá D.C., Bogotá D.C | McCombos Clásicos  | McCombo™ Grande Big Mac  | Coca-Cola  | Salsa Especial | Queso Cheddar        | TSOFT  | card         | 5416752602582580 | 11/25            | 123             |
