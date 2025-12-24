Install gradle 9.0
java 25

database creation in docker
docker run --name postgres -e POSTGRES_PASSWORD=yamamato -p 5432:5432 -v /media/shreevardhan/D/databases/postgres/Microservices/data:/var/lib/postgresql/data -d postgres


change data source url to local ipconfig url on COMMON-DATA-SOURCE.yml
use sh build.sh etc in the scripts folder.
in order service we have initial database scripts to run in database

in case of issues with running shell scripts due to line ending issues.

dos2unix $(find . -type f -name "*.sh")
dos2unix $(find . -type f -name "*Dockerfile")
git config --global core.autocrlf input
git add --renormalize $(find . -name "*.sh" "*Dockerfile")

username: shree
password to be saved in database'{bcrypt}$2a$10$NOtvXNdcmPymn6iSJSBdmefLnFADCu0GeMMCSBRkCa7W1Mn7z5vGq' = 'shree'