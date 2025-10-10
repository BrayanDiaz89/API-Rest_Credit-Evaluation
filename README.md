# 💳 Credit Score

🚀 **Credit Score** es un microservicio inteligente desarrollado en **Java (Spring Boot)** que evalúa solicitudes de crédito aplicando un conjunto de **reglas de negocio**.  
El objetivo es determinar si un crédito debe ser **aprobado o rechazado**, basándose en criterios dinámicos como el nivel de ingresos, deudas, historial y perfil del solicitante.

---

## 🧠 Concepto general

El proyecto se diseñó siguiendo principios de **programación orientada a objetos (POO)** y **patrones de diseño**, separando claramente:

- 🧩 **Lógica de negocio (Business Rules)**  
- ⚙️ **Procesamiento del flujo (Credit Engine)**  
- 🧾 **Estructuras de datos (DTOs y enums)**  

Cada regla implementa una interfaz común y puede añadirse o eliminarse sin afectar el resto del sistema, facilitando la **escalabilidad** y el **mantenimiento**.

---


### 📘 Clases principales

| Clase / Interfaz | Descripción |
|------------------|-------------|
| `CreditRequestDTO` | Contiene los datos de entrada de la solicitud (ingresos, deudas, edad, etc.) |
| `CreditResponseDTO` | Devuelve el resultado del análisis (aprobado, nivel de riesgo, mensaje) |
| `CreditRule` | Interfaz que define los métodos `appliesTo()` y `evaluate()` para las reglas |
| `CreditRisk` | Enum que clasifica el riesgo del solicitante: `HIGH_RISK`, `MEDIUM_RISK`, `LOW_RISK` |
| `CreditEngine` | Clase que evalúa todas las reglas y determina el resultado final |

---

## ⚖️ Reglas de negocio

El motor de análisis crediticio aplica las siguientes **reglas inteligentes**, priorizando los **rechazos o riesgos altos** antes que las aprobaciones seguras ✅🚫  

| 🧩 Regla | 🧠 Descripción | 🎯 Condición principal | 🧾 Resultado |
|-----------|----------------|------------------------|--------------|
| 🧓 **Edad mínima para crédito** | Rechaza solicitudes de menores de 21 años (excepto créditos educativos). | `edad < 21 y tipo ≠ EDUCATIONAL_LOAN` | ❌ Rechazo directo por edad insuficiente. |
| 🎓 **Créditos educativos para menores** | Permite créditos educativos a menores de 21 años, pero exige un codeudor. | `edad < 21 y tipo = EDUCATIONAL_LOAN` | ⚠️ Riesgo alto — requiere codeudor. |
| 📉 **Historial crediticio deficiente** | Si el puntaje crediticio es inferior a 500, el crédito se rechaza automáticamente. | `puntaje_credito < 500` | ❌ Rechazo por historial crediticio insuficiente. |
| 📊 **Historial crediticio intermedio** | Si el puntaje está entre 500 y 700, se aprueba el crédito con condiciones limitadas. | `500 ≤ puntaje_credito < 700` | ⚠️ Riesgo medio — crédito aprobado con restricciones. |
| 💸 **Monto de préstamo desproporcionado** | Rechaza solicitudes en las que el monto solicitado supera 10 veces el ingreso mensual. | `monto_solicitado > ingreso × 10` | ❌ Rechazo por monto no acorde al ingreso. |
| 🏠 **Plazo no realista para hipoteca** | Detecta plazos demasiado cortos para créditos hipotecarios y los rechaza. | `tipo = MORTGAGE_LOAN y plazo < 60 meses` | ❌ Rechazo por plazo no viable. |

👉 **El sistema de decisión garantiza que:**  
- Se apliquen todas las reglas de forma automática (inyectadas con Spring).  
- Las reglas **negativas (riesgo alto o rechazo)** tengan prioridad sobre las aprobaciones.  
- La arquitectura sea **abierta a la extensión**,

---

## 🧮 Motor de decisión

El motor de evaluación sigue el siguiente flujo:

1. 🔍 Filtra las reglas aplicables según la solicitud (`appliesTo()`).
2. ⚙️ Evalúa cada regla (`evaluate()`).
3. 🧩 Ordena los resultados priorizando rechazos y alto riesgo:
   ```java
   .min(Comparator.comparing(CreditResponseDTO::creditApproved)
       .thenComparing(CreditResponseDTO::riskLevel))
4. ✅​ Devuelve la decisión final o una aprobación por defecto.
5. 
## 🧰 Tecnologías utilizadas

| Tecnología | Uso principal |
|-------------|----------------|
| ☕ **Java 17+** | Lenguaje base del proyecto |
| 🌱 **Spring Boot** | Framework principal del backend |
| 📦 **Maven** | Gestión de dependencias |
| 🧩 **Jakarta Validation** | Validaciones de datos de entrada |
| 🧮 **Streams & Lambdas** | Procesamiento funcional de reglas |
| 🧑‍💻 **POO y Enums** | Modelado de entidades y riesgos crediticios |
| ⚙️ **JUnit** | Validación de pruebas unitarias para cada componente |
| 🔍 **Swagger** | Documentación de la API. |

---

## 🧠 Ejemplo de flujo

### 📤 **Solicitud (Request)**

```json
{
  "name": "brayan",
  "email": "brayan@gmail.com",
  "phone": "3545115415",
  "age": 24,
  "income": 2500,
  "typeOfLoan": "EDUCATIONAL_LOAN",
  "loanAmount": 1900,
  "loanTermMonths": 24,
  "creditHistoryScore": 600
}

```

### 📤 **Response**

```json
{
  "name": "brayan",
  "email": "brayan@gmail.com",
  "phone": "3545115415",
  "creditApproved": true,
  "riskLevel": "MEDIUM_RISK",
  "maxEligibleAmount": 7500,
  "recomendation": "Se aprueba el crédito, pero con un límite de préstamo reducido."
}

```

## 🧩 Principios aplicados

- **Open/Closed Principle (SOLID)** → puedes agregar nuevas reglas de negocio sin modificar el servicio principal.  
- **Single Responsibility Principle** → cada regla tiene una única responsabilidad.
- **Patron de diseño** → Strategy
- **Encapsulación y bajo acoplamiento**, para favorecer la extensibilidad.  
- **Uso de Streams y Lambdas** para hacer el código más declarativo, limpio y eficiente.

---

---

## 🌟 Posibles mejoras futuras

✅ Implementar **persistencia** con PostgreSQL  
✅ Añadir **API REST completa** con controladores de solicitud  
✅ Registrar auditorías con **SLF4J / Logback**  
✅ Crear **tests unitarios** para cada regla con JUnit y Mockito  
✅ Documentar endpoints con **Swagger (OpenAPI)**  

---

## 👨‍💻 Autor

**Brayan Díaz**  
Desarrollador apasionado por el aprendizaje continuo, la arquitectura limpia y la construcción de software mantenible.  
🌐 [GitHub: BrayanDiaz89](https://github.com/BrayanDiaz89)

---

> “El código limpio no solo resuelve un problema, cuenta una historia que otros pueden continuar.” ✨  
> — *Brayan Díaz*



