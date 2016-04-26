
# make preparePM104_DEVE MYSQL_ROOT_PASSWORD=xxxxxx

resetDB_test:createB_test
	if [ `find . -name PM*.tgz|wc -l` -gt 0 ]; then rm PM*.*; fi;
	wget http://perfumeriamarlen.dyndns.org:7080/exportPM91-1.0-SNAPSHOT/eb/PM.tgz -O PM.tgz
	tar xvfz PM.tgz
	mysql --default-character-set=utf8 -u root -p$(MYSQL_ROOT_PASSWORD) PMARLEN_DB_PROD < PM.sql
	mvn clean compile exec:java -Dexec.mainClass=com.pmarlen.migration.ImportData -Dexec.args="root $(MYSQL_ROOT_PASSWORD) jdbc:mysql://localhost/PMARLEN_DB_PROD?characterEncoding=UTF-8 root root jdbc:mysql://localhost/PMDB103_DEVE?characterEncoding=UTF-8"

reloadDB_test:createB_test
	tar xvfz PM.tgz
	mysql --default-character-set=utf8 -u root -p$(MYSQL_ROOT_PASSWORD) PMARLEN_DB_PROD < PM.sql
	mvn clean compile exec:java -Dexec.mainClass=com.pmarlen.migration.ImportData -Dexec.args="root $(MYSQL_ROOT_PASSWORD) jdbc:mysql://localhost/PMARLEN_DB_PROD?characterEncoding=UTF-8 root root jdbc:mysql://localhost/PMDB103_DEVE?characterEncoding=UTF-8"

resetDB_prod:createB_prod
	if [ `find . -name PM*.tgz|wc -l` -gt 0 ]; then rm PM*.*; fi;
	wget http://perfumeriamarlen.dyndns.org:7080/exportPM91-1.0-SNAPSHOT/eb/PM.tgz -O PM.tgz
	tar xvfz PM.tgz
	mysql --default-character-set=utf8 -u root -p$(MYSQL_ROOT_PASSWORD) PMARLEN_DB_PROD < PM.sql
	mvn clean compile exec:java -Dexec.mainClass=com.pmarlen.migration.ImportData -Dexec.args="root $(MYSQL_ROOT_PASSWORD) jdbc:mysql://localhost/PMARLEN_DB_PROD?characterEncoding=UTF-8 PMDB103_PROD_USR PMDB103_PROD_PWD jdbc:mysql://localhost/PMDB103_PROD?characterEncoding=UTF-8"

createB_test:
	make -C ../pmarlen-commons/db_resources init_DB_DEVE MYSQL_ROOT_PASSWORD=$(MYSQL_ROOT_PASSWORD)

createB_prod:
	make -C ../pmarlen-commons/db_resources init_DB_PROD MYSQL_ROOT_PASSWORD=$(MYSQL_ROOT_PASSWORD)

mmigrateTo_PM104_DEVE:
	mysql --default-character-set=utf8 -u root -p$(MYSQL_ROOT_PASSWORD) PMDB104_DEVE  < /usr/local/pmarlen_mysqldump/PMDB103_PROD_mysqldump_LAST.sql	
	mysql --default-character-set=utf8 -u root -p$(MYSQL_ROOT_PASSWORD) PMDB104_DEVE  < ../pmarlen-commons/db_resources/sql/MIGRATION.mysql.sql
	mvn compile exec:java -Dexec.mainClass=com.pmarlen.migration.MigrarInventarioPM901_PM1041 -Dexec.args="root $(MYSQL_ROOT_PASSWORD) jdbc:mysql://localhost/PMDB104_DEVE?characterEncoding=UTF-8  2  PMARLEN_PROD PMARLEN_PROD_PASSWORD jdbc:derby:/Users/alfredo/PM901Sucursales/PMS1/PMarlen_DB "
	mvn compile exec:java -Dexec.mainClass=com.pmarlen.migration.MigrarInventarioPM901_PM1041 -Dexec.args="root $(MYSQL_ROOT_PASSWORD) jdbc:mysql://localhost/PMDB104_DEVE?characterEncoding=UTF-8  3  PMARLEN_PROD PMARLEN_PROD_PASSWORD jdbc:derby:/Users/alfredo/PM901Sucursales/PMS2/PMarlen_DB "
	mvn compile exec:java -Dexec.mainClass=com.pmarlen.migration.MigrarInventarioPM901_PM1041 -Dexec.args="root $(MYSQL_ROOT_PASSWORD) jdbc:mysql://localhost/PMDB104_DEVE?characterEncoding=UTF-8  4  PMARLEN_PROD PMARLEN_PROD_PASSWORD jdbc:derby:/Users/alfredo/PM901Sucursales/PMS3/PMarlen_DB "

