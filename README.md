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

El motor actualmente evalúa las siguientes reglas:

| Regla | Descripción | Resultado posible |
|--------|--------------|------------------|
| 💰 `IncomeRule` | Evalúa si el solicitante tiene ingresos suficientes según el monto solicitado. | Aprobación o rechazo |
| 🧾 `DebtRule` | Verifica si el nivel de endeudamiento actual supera el límite permitido. | Rechazo si el porcentaje es alto |
| 📉 `CreditHistoryRule` | Analiza el historial crediticio del solicitante. | Riesgo alto si tiene atrasos o deudas |
| 🧓 `AgeRule` | Rechaza solicitudes fuera del rango permitido (por ejemplo, menores de edad). | Rechazo directo |
| 📊 `EmploymentRule` | Considera la estabilidad laboral y el tipo de contrato. | Riesgo bajo si es estable |

👉 El sistema prioriza las **reglas negativas** (rechazos) antes de las positivas, garantizando decisiones seguras.

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



