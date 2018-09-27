# Code Challenge GrupoZap

> Para resolução do desavio foi escolhida a opção 2 -> **Fazer uma API (backend)**

### Tecnologias Mais Relevantes Utilizadas:
* [Java 8](https://www.java.com/pt_BR/download/faq/java8.xml)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Data MongoDB](https://projects.spring.io/spring-data-mongodb/)
* [Lombok](https://projectlombok.org/)
* [Jackson](https://github.com/FasterXML/jackson)
* [Junit](https://junit.org/junit4/)
* [Mockito](https://site.mockito.org/)
* [Hamcrest](http://hamcrest.org/)
* [Gradle](https://gradle.org/)
* [MongoDB](https://www.mongodb.com/)
* [Docker](https://www.docker.com/)

### Requisitos:

* [JDK 8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html) instalado e configurado no path.
* [Docker](https://docs.docker.com/install/) instalado e configurado no path (deploy).
* [Lombok IDEs](https://projectlombok.org/setup/overview) para importação do projeto em IDE.

### Exceutando o Projeto:

> O [Gradle Wrrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) foi utilizado no projeto, então não é obrigatório sua instalação.Para executar em Windows use **gradlew** em Linux **./gradlew**

#### Excecutando Localmente:
```
gradlew bootRun
```
> O primeiro startup pode levar alguns minutos já que o Gradle resolverá todas as dependências do projeto e o Spring Boot realizará o download do MongoDB no seu startup.

#### Exectudando os Testes:
```
gradle test
```
> Os Testes **ImmobileRepositoryTest** e **ImmobileControllerTest** utilizam o contexto do [Spring Boot](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html) e como estamos realizando o load dos dados em memória ( MongoDB Embarcado) no startup do Spring Boot, estes testes estão honerando o tempo total da execução.Entretanto, em um cenário mais real de desenvolvimento não estaríamos trabalhando com dados em memória assim melhorando a performance dos testes. 

#### Deploy (Docker):
```
gradlew distDocker
docker run -p 8080:8080 grupozap-challenge:0.0.1
```

### Testando a API:

#### GET dos Imóveis para Aluguel:
```
http://localhost:8080/properties/rental?portal=vivareal&page=0
```
#### GET dos Imóveis para Venda:
```
http://localhost:8080/properties/sale?portal=zap&page=0
```
> Onde **portal** é o nome do portal e **page** é o número da página de paginação.Caso o portal não seja informado o content do output será retornado vazio e caso o page não seja informado o valor 0 (default) será utilizado.

### JSON de Output:

#### Imóveis (Content)
```
{
   "content":[
      {
         "id":"95ea6fceedb0",
         "usableAreas":90,
         "listingType":"USED",
         "createdAt":"2017-02-24T22:49:23.000+0000",
         "listingStatus":"ACTIVE",
         "parkingSpaces":2,
         "updatedAt":"2017-02-24T22:49:23.000+0000",
         "owner":false,
         "images":[
            "https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/c5daa0e282b925cd5feabb7aa38273ba.jpg"
         ],
         "address":{
            "city":"São Paulo",
            "neighborhood":"Vila Sao Francisco",
            "geoLocation":{
               "precision":"APPROXIMATE",
               "location":{
                  "lon":-46.494214,
                  "lat":-23.521604
               }
            }
         },
         "bathrooms":3,
         "bedrooms":3,
         "pricingInfos":{
            "period":null,
            "yearlyIptu":0,
            "price":620000,
            "rentalTotalPrice":0,
            "businessType":"SALE",
            "monthlyCondoFee":0
         }
      },
      {
         "id":"d70ee5d4970f",
         "usableAreas":55,
         "listingType":"USED",
         "createdAt":"2016-03-11T01:01:10.000+0000",
         "listingStatus":"ACTIVE",
         "parkingSpaces":2,
         "updatedAt":"2016-03-11T01:01:10.000+0000",
         "owner":false,
         "images":[
            "https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/f397c769508965effdf227598bc11465.jpg",
            "https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/99cb16624116fec5fab445d7cd79b6ad.jpg",
            "https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/2fbdaac44021a414d845214fb6e8b6e3.jpg",
            "https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/eb3e04ea242766ca3608a36195bb6c4c.jpg",
            "https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/76087a856315074cc961aac0eb990552.jpg"
         ],
         "address":{
            "city":"São Paulo",
            "neighborhood":"Jaguare",
            "geoLocation":{
               "precision":"APPROXIMATE",
               "location":{
                  "lon":-46.747535,
                  "lat":-23.544274
               }
            }
         },
         "bathrooms":1,
         "bedrooms":2,
         "pricingInfos":{
            "period":null,
            "yearlyIptu":0,
            "price":424000,
            "rentalTotalPrice":0,
            "businessType":"SALE",
            "monthlyCondoFee":0
         }
      },....
```
#### Informações da paginação:
```
....
],
   "pageable":{
      "sort":{
         "unsorted":true,
         "sorted":false
      },
      "pageSize":20,
      "pageNumber":1,
      "offset":20,
      "unpaged":false,
      "paged":true
   },
   "totalElements":4528,
   "totalPages":227,
   "last":false,
   "numberOfElements":20,
   "sort":{
      "unsorted":true,
      "sorted":false
   },
   "first":false,
   "size":20,
   "number":1
}
```

