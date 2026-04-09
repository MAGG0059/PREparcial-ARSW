# 1. RabbitMQ
docker run -d --name rabbitmq-par2ARSW -p 5672:5672 -p 15672:15672 rabbitmq:3-management

# 2. Servidor
cd ManejadorOfertas
mvn clean compile
mvn exec:java

# 3. Clientes (3 o mas terminales)
cd QuienDaMasApp
mvn clean compile
mvn exec:java