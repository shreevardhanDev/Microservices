Install gradle 9.0
java 25

database creation in docker
docker run -d \
  --name postgres \
  --network mynetwork \
  -p 5432:5432 \
  -e POSTGRES_PASSWORD=yamamato \
  -e PGDATA=/var/lib/postgresql/data/pgdata \
  -v /media/shreevardhan/D/databases/postgres/Microservices/data:/var/lib/postgresql/data \
  postgres



docker exec -i postgres pg_dump -U postgres --format=c --large-objects --encoding "UTF8" --no-owner --no-privileges --no-tablespaces --verbose --schema "keycloak" postgres > /home/shreevardhan/Documents/keycloak.backup

docker exec -i postgres pg_dump -U postgres --format=c --large-objects --encoding "UTF8" --no-owner --no-privileges --no-tablespaces --verbose --schema "public" postgres > /home/shreevardhan/Documents/public.backup


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


All required files to start and run containers are provided in the scripts folder. 
docker composes, script files to start them, kubernetes files to create kind cluster and run all the services.

For splunk, need to create the token required for ote-config.yaml using the menu Settings -> Data Inputs -> HTTP Event Collector -> New Token
select history, main and summary indexes. but only main for the default index.

once the token is generated copy that to otel-config.yaml file under docker-compose folder and copy the file to otel container and restart it.