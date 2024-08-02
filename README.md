# KodeKraft
Rest API  Testing framework with RestAssured
#Blog
https://khemlall-mangal.medium.com/kodekraft-build-a-restassured-java-rest-api-automation-framework-from-scratch-ci-afa309f23460

Preq:
Install IntelliJ, Rest Assured, and Java JDK, MAVEN Need a Spotify developer account

git clone

Open up the project in Intellij and packages should be installed based on the pom.xml file. 

You will need to update  config.properities file that contains the following field with your account information.

#client_id=enter_client_id
#client_secret=enter_client_secret
#refresh_token=enter_refresh_token
#grant_type=refresh_token
#user_id=enter_user_id


#command to Run:
mvn test -DBASE_URI="https://api.spotify.com" -DACCOUNT_BASE_URI="https://accounts.spotify.com"

Happy Testing.



