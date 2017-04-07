package org.ancloud.domain.dbmigration;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

public class CustomFlywayMigrationStrategy implements FlywayMigrationStrategy {
	Logger logger = LoggerFactory.getLogger(CustomFlywayMigrationStrategy.class);
	@Override
	public void migrate(Flyway flyway) {
		try{
			logger.info("Starts the database migration.");
			flyway.migrate();
		} catch (FlywayException ex){
			logger.error("Db migration fails.",ex);
			logger.info("Repairs the Flyway metadata table.");
			flyway.repair();
		}
	}

}
