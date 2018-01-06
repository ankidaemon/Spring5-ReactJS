### Spring WebFlux -Reactive MongoDB

  * Download and Install MongoDB
      https://www.mongodb.com/download-center
  
  * Open CMD and run MongoDB service - mongod.exe placed under /bin
    * If Failed due to non existing data/db , create directory manually
    * Run mongod.exe again
    
  * Keep the CMD with mongod.exe service, open to keep mongoDB service alive.
  
  * Open another terminal/cmd, run mongo.exe placed under /bin
  * After prompt , run - 
  
    use reactive;
    
    show collections;
    
    db.user.find();
