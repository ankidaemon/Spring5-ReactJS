#SpringSecurity-BasicAndDigestAuthentication

### Basic Authentication

###### If using http ( Not advisable with basic authentication )
    * curl -i http://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    * curl -i --user <username>:<password> http://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    
###### If using https
    * curl -i -k https://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    * curl -i -k --user <username>:<password> https://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    
    
### Digest Authentication


###### If using http 
    * curl -i --digest http://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    * curl -i --digest --user <username>:<password> http://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    
###### If using https
    * curl -i -k --digest https://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
    * curl -i -k --digest --user <username>:<password> https://<localhost:port>/SpringSecurity-BasicAndDigestAuthentication/
