Feature: Flujo  Delivery Android
  Como usuario quiero realizar un pedido delivery

  Background:
    Given Abrir Aplicacion en BrowserStack

  Scenario Outline: flujo de pedido delivery con usuario registrado en: ,<Configuracion>,<Index>
    When Ejecuto con la configuracion "<Configuracion>"
    And Seleccionar Pais <Index>
    And Presionar boton Next luego de elegir Pais <Index>
    When Dentro del mercado de Pais acceder a pedidos y hacer login con User y Pass registrada <Index>
    Then Iniciar orden tipo delivery indicar Direccion seleccionar Categoria y Producto <Index>
    And Seleccionar bebida Bebida <Index>
    And Quitar IngredienteElim y confirmar orden <Index>
    And Editar IngredienteExtra y guardar pedido <Index>
    When Realizar pago, anadir codigo promocional, seleccionar tipo de pago y presionar boton  pagar <Index>
    Then Dentro de pasarela de pago de delivery del pais ingresar datos de tarjeta de credito con numero de tarjeta, fecha de vencimiento, codigo de seguridad y nombre tarjeta <Index>
    @regresion
    Examples:
      | Configuracion     | Index |
      | regresionDelivery | 0     |
      | regresionDelivery | 1     |
      | regresionDelivery | 2     |
      | regresionDelivery | 3     |
      | regresionDelivery | 4     |
      | regresionDelivery | 5     |
      | regresionDelivery | 6     |
      | regresionDelivery | 7     |
      | regresionDelivery | 8     |
    @smokeTest
    Examples:
      | Configuracion     | Index |
      | smokeTestDelivery | 0     |
      | smokeTestDelivery  | 1     |
    @fastRegresion
    Examples:
      | Configuracion     | Index |
      | regresionDelivery | 0     |
      | regresionDelivery | 1     |
      | regresionDelivery | 2     |
      | regresionDelivery | 3     |

#  Scenario Outline: flujo de pedido delivery con usuario No registrado en:  - "<Configuracion>" <Index>
#    When Ejecuto con la configuracion "<Configuracion>"
#    And Seleccionar Pais <Index>
#    And Presionar boton Next luego de elegir Pais <Index>
#    When Dentro del mercado de Pais acceder a pedidos y hacer login con User y Pass registrada <Index>
#    Then Iniciar orden tipo delivery indicar Direccion seleccionar Categoria y Producto <Index>
#    And Seleccionar bebida Bebida <Index>
#    And Quitar IngredienteElim y confirmar orden <Index>
#    And Editar IngredienteExtra y guardar pedido <Index>
#    When Realizar pago, anadir codigo promocional, seleccionar tipo de pago y presionar boton  pagar <Index>
#    Then Dentro de pasarela de pago de delivery del pais ingresar datos de tarjeta de credito con numero de tarjeta, fecha de vencimiento, codigo de seguridad y nombre tarjeta <Index>
#    @regresion
#    Examples:
#      | Configuracion     | Index |
#      | regresionDelivery | 0     |
#      | regresionDelivery | 1     |
#      | regresionDelivery | 2     |
#      | regresionDelivery | 3     |
#      | regresionDelivery | 4     |
#      | regresionDelivery | 5     |
#      | regresionDelivery | 6     |
#      | regresionDelivery | 7     |
#      | regresionDelivery | 8     |
#    @smokeTest
#    Examples:
#      | Configuracion     | Index |
#      | smokeTestDelivery | 0     |
#      | smokeTestDelivery | 1     |
#    @fastRegresion
#    Examples:
#      | Configuracion     | Index |
#      | regresionDelivery | 0     |
#      | regresionDelivery | 1     |
#      | regresionDelivery | 2     |
#      | regresionDelivery | 3     |


