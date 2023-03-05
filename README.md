# we will be implementing a Spring Boot 3 + RabbitMQ example to genrate more than queue and consume message from it


## Download and start RabbitMQ instance
  RabbitMQ is open source message broker software (sometimes called message-oriented middleware) that implements the Advanced Message Queuing Protocol (AMQP). The  RabbitMQ server is written in the Erlang programming language and is built on the Open Telecom Platform framework for clustering and failover .

  Since RabbitMQ is built on top of Erlang, we will first need to install Erlang. Got to the Erlang downloads page and download the erlang binary file for windows which is an executable.
  Next run the binary file downloaded and install erlang on your machine.
  Go to RabbitMQ downloads page and download RabbitMQ installation.
  Run this exe and install RabbitMQ on your machine.
  We will now start Rabbit. The above installation should have installed the RabbitMQ command prompt. Open it.
  #### Go to the RabbitMQ Server Location and use the command as follows-
     rabbitmq-server start
   
  Next we will install the RabbitMQ plugin which will give use the RabbitMQ Management Console which is accessible using the browser.
   #### For this use the command as follows-
    rabbitmq-plugins.bat enable rabbitmq_management
  
  Next go to localhost:15672.We will see the RabbitMQ console. The default username and password is guest.

## What are the types of exchanges available in RabbitMQ?
  There are 4 types of exchanges available in RabbitMQ.
  ### 1- Direct -
    Based on the routing key a message is sent to the binding queue having the same routing key.
    The routing key of exchange and the binding queue have to be an exact match.
  ### 2- Fanout - 
    The message is routed to all the available bounded queues.
    The routing key if provided is completely ignored. So this is a kind of publishe-subscribe design pattern.
  ### 3- Topic -
    Here again the routing key is made use of. But unlike in direct exchange type,
    here the routing key of the exchange and the bound queues should not necessarily be an exact match.
    Using regular expressions like wildcard we can send the exchange to multiple bound queues.
  ### 4- Headers - 
    In this type of exchange the routing queue is selected based on the criteria specified in the headers instead of the routing key.
    This is similar to topic exchange type, but here we can specify complex criteria for selecting routing queues.  
  ### Default 
    Default Exchange in RabbitMQ is not a special type of Exchange in RabbitMQ like Direct Exchange or Topic Exchange.
    It is a special Direct Exchange with an empty name
    
## How to consume messages from RabbitMQ using Spring Boot?
  ### Manually
    To consume message from a RabbitMQ Queue we need to implement RabbitMQ Rest using Spring Boot 
    and we need to get count of Messages and data of its.
  ### Automatically
    To consume message from a RabbitMQ Queue we need to implement RabbitMQ listeners using Spring Boot .
    
## How to implement Retry and Error Handling for RabbitMQ?
  Retry messages on exception and if exception still exists after maximum retries
  then put message in a dead letter queue where it can be analyzed and corrected later.
## What is a Dead Letter Queue?
  In message queueing the dead letter queue is a service implementation
  to store messages that meet one or more of the following failure criteria:
    Message that is sent to a queue that does not exist.
    
    Queue length limit exceeded.
    Message length limit exceeded.
    Message is rejected by another queue exchange.
    Message reaches a threshold read counter number, because it is not consumed. Sometimes this is called a "back out queue".
