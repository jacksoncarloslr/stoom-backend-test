# stoom-backend-test

Algumas Observações...

- Usei o banco H2 para simular as operações (CREATE, UPDATE, PUT, DELETE) com as devidas configurações no application.properties

- Para buscar as coordenadas de latitude e longitude usei a biblioteca:

  <dependency>
			<groupId>com.google.maps</groupId>
			<artifactId>google-maps-services</artifactId>
			<version>0.10.1</version>
		</dependency>

No inicio até implementei via RestTemplate, porém o parse do Response seria muito trabalhoso, por isso optei por usar a lib acima, o que facilitou as coisas.

- A aplicação está rodando na porta 8081



