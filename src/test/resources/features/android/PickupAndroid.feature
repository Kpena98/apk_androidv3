Feature: Flujo Pickup Android

	Background:
		#@PRECOND_LB-1012
		Given Abrir Aplicacion en BrowserStack

	
	Scenario Outline: flujo de pedido pickup con usuario registrado en "<Pais>"
		And Seleccionar "<Pais>"
		    And Presionar boton Next luego de elegir "<Pais>"
		    When Dentro del mercado de "<Pais>" acceder a pedidos y hacer login con "<User>" y "<Pass>" registrada
		    Then Iniciar orden tipo pickup indicar "<Tienda>"  seleccionar "<Categoria>" y "<Producto>"
		    And Seleccionar bebida "<Bebida>"
		    And Quitar "<IngredienteElim>" y confirmar orden
		    And Editar "<IngredienteExtra>" y guardar pedido
		    When Realizar pago, anadir codigo promocional "<Codigo>", seleccionar tipo de pago "<TipoPago>" y presionar boton  pagar
		    Then Dentro de pasarela de pago ingresar datos de tarjeta de credito con numero de tarjeta "<NumeroTarjeta>" y fecha de vencimiento "<FechaVencimiento>" y codigo de seguridad "<CodigoSeguridad>"
		    @regresion
		    Examples:
		      | Pais        | User                       | Pass         | Tienda | Categoria|  Producto |Bebida            | IngredienteElim | IngredienteExtra | TipoPago | NumeroTarjeta| FechaVencimiento| CodigoSeguridad|
		      | Argentina   | pruebaautoqa@gmail.com     | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Brasil      | pruebaautoqabra@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Chile       | pruebaautoqachi@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Colombia    | pruebaautoqacol@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Costa Rica  | pruebaautoqacos@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | México      | pruebaautoqamex@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Panamá      | pruebaautoqapan@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Puerto Rico | pruebaautoqapuer@gmail.com | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		      | Uruguay     | pruebaautoqauru@gmail.com  | Pruebas_2022 |        |          |          |              |                 |                |                |                 |                |               |
		    @smokeTest
		    Examples:
		      | Pais   | User                      | Pass         | Tienda  | Categoria | Producto                       | Bebida            | IngredienteElim | IngredienteExtra |Codigo| TipoPago | NumeroTarjeta | FechaVencimiento| CodigoSeguridad |
		      #| Brasil | pruebaautoqabra@gmail.com | Pruebas_2022 |        |                                |                  |               |                 |                 |
		     # | Argentina | pruebaautoqa@gmail.com | Pruebas_2022 | Avenida del Libertador 2803. Olivos | McCombos |  Big Mac McCombo grande |Coca Cola| Salsa Big Mac   | Queso Cheddar    |Mercado Pago|5416752602582580|11/25|123|
		      | Chile    | pruebaautoqachi@gmail.com  | Pruebas_2022 |AHUMADA| McCombos Grandes |McCombo Grande BigMac |Coca-Cola Original|Salsa Big Mac|Queso Cheddar| TSOFT|card|5416752602582580|11/25|123|
