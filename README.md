# CSAN_lab3
Receiving a domain info via WHOIS protocol (TCP/IP), Java sockets

A WHOIS server listens on TCP port 43 for requests from WHOIS
clients.  The WHOIS client makes a text request to the WHOIS server,
then the WHOIS server replies with text content.

This program provides a simple data exchange between Server and Client. In this case, like a WHOIS protocol.
Server and Client connect via Java socket (TCP/IP) with the use of some port number(not 43).
Client enters the name of domain. At the same time Server provides an information about this domain, which is in the json file "Whois.json".
