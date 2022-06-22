@endtoend
Feature: Flujo Pickup Android
  Como usuario quiero realizar un pedido pickup

  Background:
    Given Abrir Aplicacion en BrowserStack

  Scenario Outline: flujo de pedido pickup con usuario registrado
    And Seleccionar "<Pais>"
    And Presionar boton Next luego de elegir "<Pais>"
    When Dentro del mercado de "<Pais>" acceder a pedidos y hacer login con "<User>" y "<Pass>" registrada
    Then Iniciar orden tipo pickup indicar "<Tienda>" y seleccionar "<Producto>"
    When Seleccionar "<TipoPago>" y presionar boton  pagar
    Then Dentro de pasarela de pago ingresar datos de tarjeta de credito con numero de tarjeta "<NumeroTarjeta>" y fecha de vencimiento "<FechaVencimiento>" y codigo de seguridad "<CodigoSeguridad>"
    @regresion
    Examples:
      | Pais        | User                       | Pass         | Tienda | Producto | 
      | Argentina   | pruebaautoqa@gmail.com     | Pruebas_2022 |        |          |
      | Brasil      | pruebaautoqabra@gmail.com  | Pruebas_2022 |        |          |
      | Chile       | pruebaautoqachi@gmail.com  | Pruebas_2022 |        |          |
      | Colombia    | pruebaautoqacol@gmail.com  | Pruebas_2022 |        |          |
      | Costa Rica  | pruebaautoqacos@gmail.com  | Pruebas_2022 |        |          |
      | México      | pruebaautoqamex@gmail.com  | Pruebas_2022 |        |          |
      | Panamá      | pruebaautoqapan@gmail.com  | Pruebas_2022 |        |          |
      | Puerto Rico | pruebaautoqapuer@gmail.com | Pruebas_2022 |        |          |
      | Uruguay     | pruebaautoqauru@gmail.com  | Pruebas_2022 |        |          |
    @smokeTest
    Examples:
      | Pais   | User                      | Pass         | Tienda  | Producto                       |
      | Brasil | pruebaautoqabra@gmail.com | Pruebas_2022 |         |                                |
      | Chile  | pruebaautoqachi@gmail.com | Pruebas_2022 | AHUMADA | Cajita Feliz Hamburguesa Palta |



