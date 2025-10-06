# OrangeHRM Automation (Maven multi‑module)

Solución profesional para el reto **Kata Agile Testing**: UI (Selenium + TestNG + Allure), API (Rest‑Assured) y Performance (JMeter), más CI/CD en GitHub Actions.

## Requisitos
- Java 17
- Maven 3.9+
- (Opcional) Allure CLI para `allure serve`
- (Opcional) Credenciales de BrowserStack: `BROWSERSTACK_USERNAME` y `BROWSERSTACK_ACCESS_KEY`

## Estructura
```
orangehrm-automation/
├─ ui-automation/            # Selenium + TestNG + Allure + POM
├─ api-automation/           # Rest-Assured + TestNG
├─ performance-testing/      # JMeter + plugin Maven
└─ .github/workflows/ci-cd.yml
```

## Cómo ejecutar local
```bash
mvn -q clean install
mvn -q -pl ui-automation test          # Pruebas UI
mvn -q -pl api-automation test         # Pruebas API
mvn -q -pl performance-testing verify  # Performance
```

### Allure Report
```bash
mvn -q -pl ui-automation io.qameta.allure:allure-maven:report
# o si tienes Allure CLI:
allure serve ui-automation/target/allure-results
```

## Variables útiles
- `-Dbrowser=chrome`
- `-DbaseUrl=https://opensource-demo.orangehrmlive.com`
- `-DuseBrowserStack=true -DBROWSERSTACK_USERNAME=xxx -DBROWSERSTACK_ACCESS_KEY=yyy`

Ejemplo BrowserStack:
```bash
mvn -pl ui-automation test -DuseBrowserStack=true   -DBROWSERSTACK_USERNAME=$BROWSERSTACK_USERNAME   -DBROWSERSTACK_ACCESS_KEY=$BROWSERSTACK_ACCESS_KEY
```

## Notas de API
El módulo `api-automation` trae una prueba estable contra **reqres.in** para garantizar CI verde.
Para cubrir OrangeHRM, captura las llamadas desde **Network** y crea un test nuevo (el ejemplo `testOrangeHrmPlaceholder` está deshabilitado).

## Performance
`performance-testing/scripts/login.jmx` ejecuta **50 usuarios concurrentes** por **5 minutos** contra la página de Login.
Los reportes HTML quedan en `performance-testing/target/jmeter/reports`.

## CI/CD
Pipeline en `.github/workflows/ci-cd.yml` que:
- Compila
- Ejecuta UI, API y Performance
- Genera y sube artefactos con reportes

## Buenas prácticas
- Page Object Model
- Separación por módulos
- Evidencia con Allure
- Cross-browser remoto con BrowserStack
- Parametrización por propiedades Maven
