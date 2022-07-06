Feature: Flujo Pickup Android
  Como usuario quiero realizar un pedido pickup

  Background:
    Given Abrir Aplicacion en BrowserStack

  Scenario Outline: flujo de pedido pickup con usuario registrado en: ,<Configuracion>,<Index>
    When Ejecuto con la configuracion "<Configuracion>"
    And Seleccionar Pais <Index>
    And Presionar boton Next luego de elegir Pais <Index>
    When Dentro del mercado de Pais acceder a pedidos y hacer login con User y Pass registrada <Index>
    Then Iniciar orden tipo pickup indicar Tienda seleccionar Categoria y Producto <Index>
    And Seleccionar bebida Bebida <Index>
    And Quitar IngredienteElim y confirmar orden <Index>
    And Editar IngredienteExtra y guardar pedido <Index>
    When Realizar pago, anadir codigo promocional, seleccionar tipo de pago y presionar boton  pagar <Index>
    Then Dentro de pasarela de pago del pais ingresar datos de tarjeta de credito con numero de tarjeta, fecha de vencimiento, codigo de seguridad y nombre tarjeta <Index>
    @regresion
    Examples:
      | Configuracion   | Index |
      | regresionPickup | 0     |
      | regresionPickup | 1     |
      | regresionPickup | 2     |
      | regresionPickup | 3     |
      | regresionPickup | 4     |
      | regresionPickup | 5     |
      | regresionPickup | 6     |
      | regresionPickup | 7     |
      | regresionPickup | 8     |
    @smokeTest
    Examples:
      | Configuracion   | Index |
      | smokeTestPickup | 0     |
      | smokeTestPickup | 1     |
    @fastRegresion
    Examples:
      | Configuracion   | Index |
      | regresionPickup | 0     |
      | regresionPickup | 1     |
      | regresionPickup | 2     |
      | regresionPickup | 3     |

#  Scenario Outline: flujo de pedido pickup con usuario No registrado en:  - "<Configuracion>" <Index>
#    When Ejecuto con la configuracion "<Configuracion>"
#    And Seleccionar Pais <Index>
#    And Presionar boton Next luego de elegir Pais <Index>
#    When Dentro del mercado de Pais acceder a pedidos y hacer login con User y Pass registrada <Index>
#    Then Iniciar orden tipo pickup indicar Tienda seleccionar Categoria y Producto <Index>
#    And Seleccionar bebida Bebida <Index>
#    And Quitar IngredienteElim y confirmar orden <Index>
#    And Editar IngredienteExtra y guardar pedido <Index>
#    When Realizar pago, anadir codigo promocional, seleccionar tipo de pago y presionar boton  pagar <Index>
#    Then Dentro de pasarela de pago del pais ingresar datos de tarjeta de credito con numero de tarjeta, fecha de vencimiento, codigo de seguridad y nombre tarjeta <Index>
#    @regresion
#    Examples:
#      | Configuracion   | Index |
#      | regresionPickup | 0     |
#      | regresionPickup | 1     |
#      | regresionPickup | 2     |
#      | regresionPickup | 3     |
#      | regresionPickup | 4     |
#      | regresionPickup | 5     |
#      | regresionPickup | 6     |
#      | regresionPickup | 7     |
#      | regresionPickup | 8     |
#    @smokeTest
#    Examples:
#      | Configuracion   | Index |
#      | smokeTestPickup | 0     |
#      | smokeTestPickup | 1     |
#    @fastRegresion
#    Examples:
#      | Configuracion   | Index |
#      | regresionPickup | 0     |
#      | regresionPickup | 1     |
#      | regresionPickup | 2     |
#      | regresionPickup | 3     |
