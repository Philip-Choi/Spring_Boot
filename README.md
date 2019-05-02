# Spring_Boot

This demonstrates Restful Web Application which sends Tokimon class(Java Bean Model) to clients (view)
through server (Controller).

Run client application and server application separately.

Tokimon is the class for Java Bean which gets converted into JSON string format and transferred between client & server

3 HTTP methods are implemented (GET, POST, DELETE) that are accessable by clients.

JavaFX is used to create intuitive UI with varaiety of sliders and buttons to create Java Bean

ResponseEntity<String>(HttpStatus.####) is used to send HTTP status to the client upon client's request
